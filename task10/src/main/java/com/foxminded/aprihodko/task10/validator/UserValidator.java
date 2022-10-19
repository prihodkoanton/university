package com.foxminded.aprihodko.task10.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.services.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getName().length() < 8 || user.getName().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByName(user.getName()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPasswordHash().length() < 8 || user.getPasswordHash().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordHash().equals(user.getPasswordHash())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
