package com.warehouse.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.warehouse.dto.UserDTO;
import com.warehouse.entity.User;
import com.warehouse.manager.CustomUserDetailsManager;
import com.warehouse.manager.UserManager;
import com.warehouse.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserManager userManager;

    @Autowired
    UserValidator userValidator;

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
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcomeUser(Principal principal, ModelMap map) {
        User user = userManager.findByLogin(principal.getName());
        boolean admin = user.getRole().getName().equals("ROLE_ADMIN");
        map.put("user",user);
        if (admin) {
            return "adminHome";
        }
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        return "userHome";
    }

    @GetMapping("/awaitingAccess")
    public String awaitingAccess() {
        return "approvalWaitingPage";
    }
}
