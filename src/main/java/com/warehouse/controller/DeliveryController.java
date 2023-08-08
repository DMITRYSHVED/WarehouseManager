package com.warehouse.controller;

import com.warehouse.dto.DeliveryDTO;
import com.warehouse.dto.DeliveryProductMapDTO;
import com.warehouse.entity.Delivery;
import com.warehouse.entity.User;
import com.warehouse.manager.*;
import com.warehouse.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/delivery")
@Slf4j
public class DeliveryController {

    @Autowired
    private DeliveryManager deliveryManager;

    @Autowired
    private ProviderManager providerManager;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private Helper helper;

    @GetMapping("/createDelivery")
    public String createDelivery(ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("products", productManager.getProducts());
        map.put("providers", providerManager.getProviders());
        map.put("deliveryDTO", new DeliveryDTO());
        map.put("productList", deliveryManager.getProductList());
        return "createDelivery";
    }

    @PostMapping("/createDelivery")
    public String saveDelivery(@ModelAttribute @Valid DeliveryDTO deliveryDTO, BindingResult bindingResult,
                               Principal principal, RedirectAttributes redirectAttributes) {
        User user = userManager.findByLogin(principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/delivery/createDelivery";
        }
        deliveryManager.saveDelivery(deliveryDTO);
        log.info("Пользователь " + user.getLogin() + " запланировал поставку на " +
                helper.formatDate(deliveryDTO.getDate()));
        return "redirect:/delivery/deliveries";
    }

    @PostMapping("/createDeliveryProductList")
    public String createDeliveryProductList(@ModelAttribute @Valid DeliveryProductMapDTO deliveryProductMapDTO,
                                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/delivery/createDelivery";
        }
        deliveryManager.setUpProductList(deliveryProductMapDTO);
        return "redirect:/delivery/createDelivery";
    }

    @GetMapping("/editDelivery")
    public String getDelivery(@RequestParam(value = "id") Integer id, ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("products", productManager.getProducts());
        map.put("providers", providerManager.getProviders());
        map.put("productList", deliveryManager.getDeliveryProductLIst(deliveryManager.getById(id)));
        map.put("delivery", deliveryManager.getById(id));
        return "delivery";
    }



    @GetMapping("/deleteDelivery")
    public String deleteDelivery(@RequestParam(value = "id") Integer id, Principal principal) {
        Delivery delivery = deliveryManager.getById(id);
        deliveryManager.delete(delivery);
        log.info("Пользователь " + userManager.findByLogin(principal.getName()).getLogin() + " отменил поставку от " +
                helper.formatDate(delivery.getDate()));
        return "redirect:/delivery/deliveries";
    }

    @PostMapping("/editDelivery")
    public String updateDelivery(@ModelAttribute Delivery delivery) {
        deliveryManager.updateDelivery(delivery);
        return "redirect:/delivery/editDelivery?id=" + delivery.getId();
    }

    @GetMapping("/deliveries")
    public String getDeliveries(Principal principal, ModelMap map) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        List<Delivery> deliveries = deliveryManager.getDeliveryList();
        deliveries.sort(Comparator.comparing(Delivery::getDate));
        map.put("deliveries", deliveries);
        map.put("providers", providerManager.getProviders().stream().limit(5).collect(Collectors.toList()));
        return "deliveries";
    }

    @GetMapping("/takeDelivery")
    public String takeDelivery(@RequestParam("id") Integer id, Principal principal) {
        Delivery delivery = deliveryManager.getById(id);
        deliveryManager.takeDelivery(delivery);
        log.info("Пользователь " + userManager.findByLogin(principal.getName()).getLogin() + " принял поставку от " +
                helper.formatDate(delivery.getDate()));
        return "redirect:/storage";
    }

    @GetMapping("/deleteProducts")
    public String deleteProduct() {
        deliveryManager.deleteProductList();
        return "redirect:/delivery/createDelivery";
    }
}
