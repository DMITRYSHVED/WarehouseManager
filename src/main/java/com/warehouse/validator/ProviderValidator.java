package com.warehouse.validator;

import com.warehouse.entity.Provider;
import com.warehouse.manager.ProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProviderValidator implements Validator {

    @Autowired
    ProviderManager providerManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return Provider.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Provider provider = (Provider) target;

        for (int i = 0; i < providerManager.getProviders().size(); i++) {
            if (providerManager.getProviders().get(i).getCompanyName().equals(provider.getCompanyName())) {
                errors.rejectValue("companyName", "DUPLICATE", "вы уже сотрудничаете с " +provider.getCompanyName());
            }
        }
    }
}
