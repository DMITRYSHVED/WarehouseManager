package com.warehouse.controller;

import com.warehouse.entity.Provider;
import com.warehouse.entity.User;
import com.warehouse.manager.ProviderManager;
import com.warehouse.manager.UserManager;
import com.warehouse.validator.ProviderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderManager providerManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ProviderValidator providerValidator;

    @GetMapping("/editProvider")
    public String getProvider(@RequestParam(value = "id") Integer id, ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("provider",providerManager.getById(id));
        return "provider";
    }

    @PostMapping("/editProvider")
    public String updateProvider(@ModelAttribute @Valid Provider provider, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/provider/editProvider?id=" + provider.getId();
        }
        providerManager.updateProvider(provider);
        return "redirect:/provider/editProvider?id=" + provider.getId();
    }

    @PostMapping("/createProvider")
    public String createProvider(@ModelAttribute @Valid Provider provider, BindingResult bindingResult,
                                 Principal principal, RedirectAttributes redirectAttributes) {
        providerValidator.validate(provider,bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/provider/providers";
        }
        providerManager.saveProvider(provider);
        log.info("Пользователь " + userManager.findByLogin(principal.getName()).getLogin() + " начал сотрудничество с " +
                provider.getCompanyName());
        return "redirect:/provider/providers";
    }

    @GetMapping("/providers")
    public String getProviders(ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("providers", providerManager.getProviders());
        return "providers";
    }

    @GetMapping("/delete")
    public String deleteProvider (@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        Provider provider = providerManager.getById(id);

        if (!providerManager.providerIsActive(provider)) {
            System.out.println(providerManager.providerIsActive(provider));
            providerManager.deleteProvider(provider);
            return "redirect:/provider/providers";
        } else {
            FieldError fieldError = new FieldError("product", "id", "нельзя удалить " +
                    "поставщика у которого есть активные поставки");
            redirectAttributes.addFlashAttribute("errors",fieldError);
            return "redirect:/provider/editProvider?id=" + id;
        }
    }
}
