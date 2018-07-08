package com.test.api.facade.user.impl;

import com.test.api.facade.user.UserFacade;
import com.test.api.model.request.user.UserUpdateRequestModel;
import com.test.api.model.response.common.ErrorType;
import com.test.api.model.response.user.UserModel;
import com.test.core.entity.user.User;
import com.test.core.service.user.UserService;
import com.test.core.service.user.common.UserRole;
import com.test.core.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 7:10 PM
 */

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public UserModel update(final Long userId, final UserUpdateRequestModel requestModel) {
        final List<ErrorType> errors = checkRequestModelForErrors(requestModel);
        if (!CollectionUtils.isEmpty(errors)) {
            return UserModel.withErrors(errors);
        }
        final UserDto userDto = new UserDto();
        userDto.setFirstName(requestModel.getFirstName());
        userDto.setLastName(requestModel.getLastName());
        final User user = userService.updateUser(userId, userDto);
        return UserModel.fromUser(user);
    }

    @Override
    public UserModel getById(final Long userId) {
        final User user = userService.getUserById(userId);
        if(UserRole.ADMIN.equals(user.getUserRole())) {
            return UserModel.withErrors(Collections.singletonList(ErrorType.USER_INVALID_ROLE));
        }
        return UserModel.fromUser(user);
    }

    @Override
    public UserModel getByUserName(final String userName) {
        final User user = userService.getUserByUsername(userName);
        return UserModel.fromUser(user);
    }

    // Utility methods
    private List<ErrorType> checkRequestModelForErrors(final UserUpdateRequestModel requestModel) {
        return requestModel.checkForErrors();
    }
}
