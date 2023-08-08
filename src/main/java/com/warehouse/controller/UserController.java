package com.warehouse.controller;

import com.warehouse.dto.UserDTO;
import com.warehouse.entity.User;
import com.warehouse.manager.*;
import com.warehouse.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private StorageManager storageManager;

    @Autowired
    private DeliveryManager deliveryManager;

    @Autowired
    private ProductOrderManager productOrderManager;


    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }

    @GetMapping("/registration")
    public String userRegistration(ModelMap map) {
        map.put("userDTO", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute @Valid UserDTO userDTO, BindingResult bindingResult, ModelMap map) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            map.put("error", bindingResult);
            return "registration";
        }
        userManager.save(userManager.toUser(userDTO));
        log.info("Пользователь " + userDTO.getLogin() + " подал заявку на регистрацию");
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String welcomeUser(Principal principal, ModelMap map) {
        map.put("logs", userManager.getUsersLog());
        map.put("recommendations", storageManager.getStorageLog());
        User user = userManager.findByLogin(principal.getName());
        map.put("user",user);
        map.put("storageQuantity" , storageManager.getTotalQuantity());
        map.put("deliveryQuantity", deliveryManager.getDeliveryList().stream()
                .filter(delivery -> delivery.getDeliveryStatus().getId() == 1).toList().size());
        map.put("orderQuantity", productOrderManager.getProductOrders().stream()
                .filter(delivery -> delivery.getOrderStatus().getId() == 1).toList().size());
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        return "home";
    }

    @GetMapping("/awaitingAccess")
    public String awaitingAccess() {
        return "approvalWaitingPage";
    }
}
