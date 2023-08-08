package com.warehouse.validator;

import com.warehouse.dto.ProductOrderMapDTO;
import com.warehouse.manager.ProductOrderManager;
import com.warehouse.manager.StorageManager;
import com.warehouse.util.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    @Autowired
    StorageManager storageManager;

    @Autowired
    ProductOrderManager productOrderManager;


    @Override
    public boolean supports(Class<?> clazz) {
        return ProductOrderMapDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductOrderMapDTO productOrderMapDTO = (ProductOrderMapDTO) target;
        int orderedQuantity;

        for (int i = 0; i < productOrderManager.getProductList().size(); i++) {
            if (productOrderMapDTO.getProductId() == productOrderManager.getProductList().get(i).getProductId()) {
                if ((productOrderMapDTO.getQuantity() + productOrderManager.getProductList().get(i).getQuantity()) >
                        storageManager.getByProductId(productOrderMapDTO.getProductId()).getQuantity()) {
                    errors.rejectValue("quantity", "DEFICIENCY", ValidationConstants.OUT_OF_STOCK);
                }
            }
        }
        orderedQuantity = productOrderMapDTO.getQuantity();
        for (int i = 0; i < productOrderManager.getOrderedProducts().size(); i++) {
            if (productOrderMapDTO.getProductId() == productOrderManager.getOrderedProducts().get(i).getProduct().getId()) {
                orderedQuantity += productOrderManager.getOrderedProducts().get(i).getQuantity();
            }
            if (orderedQuantity > storageManager.getByProductId(productOrderMapDTO.getProductId()).getQuantity()) {
                errors.rejectValue("quantity", "DEFICIENCY", ValidationConstants.OUT_OF_STOCK);
            }
        }
        if (productOrderMapDTO.getQuantity() > storageManager.getByProductId(productOrderMapDTO.getProductId()).getQuantity()) {
            errors.rejectValue("quantity", "DEFICIENCY", ValidationConstants.OUT_OF_STOCK);
        }
        if (productOrderMapDTO.getQuantity() <= 0) {
            errors.rejectValue("quantity", "NEGATIVELY", ValidationConstants.POSITIVE_QUANTITY);
        }
    }
}
