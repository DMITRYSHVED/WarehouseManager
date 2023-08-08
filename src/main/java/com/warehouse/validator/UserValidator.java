package com.warehouse.validator;

import com.warehouse.entity.User;
import com.warehouse.manager.UserManager;
import com.warehouse.dto.UserDTO;
import com.warehouse.util.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserManager userManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;

        if (userManager.findByLogin(user.getLogin())!=null){
            errors.rejectValue("login", "DUPLICATE","логин занят");
        }
        if (userManager.findByEmail(user.getEmail())!=null){
            errors.rejectValue("email","DUPLICATE","такая почта уже используется");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword","DIFFERENT","пароли не совпадают");
        }
    }
}
