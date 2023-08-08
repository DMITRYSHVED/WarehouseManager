package com.warehouse.validator;

import com.warehouse.dto.StorageDTO;
import com.warehouse.entity.Storage;
import com.warehouse.manager.StorageManager;
import com.warehouse.util.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DiscardValidator implements Validator {

    @Autowired
    private StorageManager storageManager;


    @Override
    public boolean supports(Class<?> clazz) {
        return StorageDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StorageDTO storageDTO = (StorageDTO) target;
        Storage storage = storageManager.getById(storageDTO.getStorageId());

        if (storageDTO.getQuantity() < 0) {
            errors.rejectValue("quantity", "NEGATIVELY", ValidationConstants.POSITIVE_QUANTITY);
        }
        if (storage.getQuantity() < storageDTO.getQuantity()) {
            errors.rejectValue("quantity", "DEFICIENCY", ValidationConstants.OUT_OF_STOCK);
        }
    }
}
