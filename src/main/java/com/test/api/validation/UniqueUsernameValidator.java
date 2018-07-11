package com.test.api.validation;

import com.test.api.facade.admin.AdminFacade;
import com.test.core.service.user.exception.UserNotExistsForUserNameException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by araksgyulumyan
 * Date - 7/11/18
 * Time - 3:51 PM
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private AdminFacade adminFacade;

    public UniqueUsernameValidator(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        try {
            adminFacade.getByUserName(username);
            return false;
        } catch (final UserNotExistsForUserNameException ex) {
            return true;
        }
    }

}
