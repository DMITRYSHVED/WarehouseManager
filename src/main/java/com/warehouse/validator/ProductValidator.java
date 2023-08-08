package com.warehouse.validator;

import com.warehouse.dto.ProductDTO;
import com.warehouse.entity.Product;
import com.warehouse.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ProductValidator implements Validator {

    @Autowired
    ProductManager productManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO product = (ProductDTO) target;

        for (int i = 0; i < productManager.getProducts().size(); i++) {

            if (productManager.getProducts().get(i).getName().equals(product.getName())) {
                errors.rejectValue("name", "DUPLICATE", "в системе уже зарегистрирован такой товар");
            }
        }
    }

}
