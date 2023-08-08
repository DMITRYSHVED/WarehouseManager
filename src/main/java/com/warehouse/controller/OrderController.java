package com.warehouse.controller;

import com.warehouse.dto.ProductOrderDTO;
import com.warehouse.dto.ProductOrderMapDTO;
import com.warehouse.entity.ProductOrder;
import com.warehouse.entity.User;
import com.warehouse.manager.ProductOrderManager;
import com.warehouse.manager.StorageManager;
import com.warehouse.manager.UserManager;
import com.warehouse.util.Helper;
import com.warehouse.validator.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/productOrder")
public class OrderController {

    @Autowired
    private ProductOrderManager productOrderManager;

    @Autowired
    private StorageManager storageManager;

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private UserManager userManager;

    @Autowired
    private Helper helper;

    @GetMapping
    public String getProductOrders(Principal principal, ModelMap map) {
        List<ProductOrder> productOrders = productOrderManager.getProductOrders();
        productOrders.sort(Comparator.comparing(ProductOrder::getDate));
        User user = userManager.findByLogin(principal.getName());
        map.put("user",user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("productOrders", productOrders);
        return "productOrders";
    }

    @GetMapping("/editOrder")
    public String getOrder(@RequestParam("id") Integer id, ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", userManager.findByLogin(principal.getName()));
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("productOrder", productOrderManager.getById(id));
        map.put("products", storageManager.getStorageProducts());
        map.put("productList", productOrderManager.getDeliveryProductLIst(productOrderManager.getById(id)));
        return "productOrder";
    }

    @GetMapping("/createOrder")
    public String createOrder(ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("products", storageManager.getStorageProducts());
        map.put("productOrderDTO", new ProductOrderDTO());
        map.put("productList", productOrderManager.getProductNameList());
        return "createOrder";
    }

    @PostMapping("/createOrder")
    public String saveOrder(@ModelAttribute @Valid ProductOrderDTO productOrderDTO,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/productOrder/createOrder";
        }
        productOrderManager.saveOrder(productOrderDTO);
        log.info("Пользователь " + userManager.findByLogin(principal.getName()).getLogin() + " оформил заказ на " +
                helper.formatDate(productOrderDTO.getDate()));
        return "redirect:/productOrder";
    }

    @PostMapping("/createOrderProductList")
    public String createDeliveryProductList(@ModelAttribute @Valid ProductOrderMapDTO productOrderMapDTO,
                                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        orderValidator.validate(productOrderMapDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/productOrder/createOrder";
        }
        productOrderManager.setUpProductList(productOrderMapDTO);
        return "redirect:/productOrder/createOrder";
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("id") int id, Principal principal) {
        ProductOrder productOrder = productOrderManager.getById(id);
        productOrderManager.deleteProductOrder(productOrder);
        log.info("Польователь " + userManager.findByLogin(principal.getName()).getLogin() + " отменил заказ от " +
                helper.formatDate(productOrder.getDate()));
        return "redirect:/productOrder";
    }

    @GetMapping("/processOrder")
    public String processOrder(@RequestParam("id") int id, Principal principal) {
        ProductOrder productOrder = productOrderManager.getById(id);
        productOrderManager.processOrder(productOrder);
        log.info("Пользователь " + userManager.findByLogin(principal.getName()).getLogin() + " принял заказ от " +
                helper.formatDate(productOrder.getDate()));
        return "redirect:/productOrder";
    }

    @GetMapping("/deleteProducts")
    public String deleteProductsFromList() {
        productOrderManager.deleteOrderProducts();
        return "redirect:/productOrder/createOrder";
    }
}

