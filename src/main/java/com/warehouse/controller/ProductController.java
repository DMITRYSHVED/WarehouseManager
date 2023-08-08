package com.warehouse.controller;

import com.warehouse.dto.ProductDTO;
import com.warehouse.entity.Product;
import com.warehouse.entity.User;
import com.warehouse.manager.ProductManager;
import com.warehouse.manager.ProductTypeManager;
import com.warehouse.manager.UserManager;
import com.warehouse.validator.ProductValidator;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private ProductTypeManager productTypeManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ProductValidator productValidator;

    @GetMapping("/editProduct")
    public String getProduct(@RequestParam(value = "id") Integer id, ModelMap map, Principal principal) {
        Product product = productManager.getById(id);
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("productTypes", productTypeManager.getTypes());
        map.put("product", product);
        return "product";
    }

    @PostMapping("/editProduct")
    public String updateProduct(@ModelAttribute @Valid ProductDTO productDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/product/editProduct?id=" + productDTO.getId();
        }
        productManager.updateProduct(productDTO);
        return "redirect:/product/editProduct?id=" + productDTO.getId();
    }

    @GetMapping("/productBase")
    public String getProductBase(ModelMap map, Principal principal) {
        User user = userManager.findByLogin(principal.getName());
        map.put("user", user);
        if (!user.isApproved()) {
            return "approvalWaitingPage";
        }
        map.put("productBase", productManager.getProducts());
        map.put("productTypes", productTypeManager.getTypes());
        return "productBase";
    }

    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute @Valid ProductDTO productDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        productValidator.validate(productDTO,bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/product/productBase";
        }
        productManager.save(productDTO);
        return "redirect:/product/productBase";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") int id,
                                RedirectAttributes redirectAttributes) {
        if (productManager.productIsUseless(id)) {
            productManager.deleteProduct(productManager.getById(id));
            return "redirect:/product/productBase";
        } else {
            FieldError fieldError = new FieldError("product", "id", "нельзя удалить товар " +
                    "который используется");
            redirectAttributes.addFlashAttribute("errors",fieldError);
            return "redirect:/product/productBase";
        }
    }
}
