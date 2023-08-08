package com.warehouse.validator;

import com.warehouse.dto.StorageDTO;
import com.warehouse.entity.DiscardedProduct;
import com.warehouse.entity.Storage;
import com.warehouse.manager.DiscardedProductManager;
import com.warehouse.util.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReturnValidator implements Validator {

    @Autowired
    DiscardedProductManager discardedProductManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return StorageDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StorageDTO storageDTO = (StorageDTO) target;
        DiscardedProduct discardedProduct = discardedProductManager.getById(storageDTO.getStorageId());

        if (storageDTO.getQuantity() < 0) {
            errors.rejectValue("quantity", "NEGATIVELY", ValidationConstants.POSITIVE_QUANTITY);
        }
        if (discardedProduct.getQuantity() < storageDTO.getQuantity()) {
            errors.rejectValue("quantity", "DEFICIENCY", "Нельзя вернуть такое количество товаров");
        }
    }
}
