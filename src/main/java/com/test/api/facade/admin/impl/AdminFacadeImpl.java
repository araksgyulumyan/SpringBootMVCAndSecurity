package com.test.api.facade.admin.impl;

import com.test.api.facade.admin.AdminFacade;
import com.test.api.model.request.admin.UserCreationRequestModel;
import com.test.api.model.request.admin.UserUpdateRequestModel;
import com.test.api.model.response.common.ErrorType;
import com.test.api.model.response.user.UserModel;
import com.test.core.entity.user.User;
import com.test.core.service.user.UserService;
import com.test.core.service.user.common.UserRole;
import com.test.core.service.user.dto.UserDto;
import com.test.core.service.user.exception.UserAlreadyExistsForUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 7:27 PM
 */
@Component
public class AdminFacadeImpl implements AdminFacade {

    @Autowired
    private UserService userService;

    @Override
    public UserModel create(final UserCreationRequestModel registrationRequestModel) {
        final List<ErrorType> errors = checkRequestModelForErrors(registrationRequestModel);
        if (!CollectionUtils.isEmpty(errors)) {
            return UserModel.withErrors(errors);
        }
        try {
            final UserDto userDto = fromRegistrationRequest(registrationRequestModel);
            final User user = userService.createUser(registrationRequestModel.getUserName(), registrationRequestModel.getPassword(),
                    UserRole.USER, userDto);
            return UserModel.fromUser(user);
        } catch (final UserAlreadyExistsForUserNameException ex) {
            errors.add(ErrorType.USERNAME_ALREADY_EXISTS);
            return UserModel.withErrors(errors);
        }
    }

    @Override
    public UserModel update(final Long userId, final UserUpdateRequestModel requestModel) {
        final List<ErrorType> errors = checkRequestModelForErrors(requestModel);
        if (!CollectionUtils.isEmpty(errors)) {
            return UserModel.withErrors(errors);
        }
        try {
            final UserDto userDto = new UserDto();
            userDto.setFirstName(requestModel.getFirstName());
            userDto.setLastName(requestModel.getLastName());
            final User user = userService.updateUser(userId, userDto);
            return UserModel.fromUser(user);
        } catch (final UserAlreadyExistsForUserNameException ex) {
            errors.add(ErrorType.USERNAME_ALREADY_EXISTS);
            return UserModel.withErrors(errors);
        }
    }

    @Override
    public void remove(final Long userId) {
        Assert.notNull(userId, "User id should not be null");
        userService.removeUserById(userId);
    }

    @Override
    public List<UserModel> getUsers() {
        final List<User> users = userService.getUsers();
        final List<UserModel> models = new ArrayList<>(users.size());
        for (final User user : users) {
            models.add(UserModel.fromUser(user));
        }
        return models;
    }

    @Override
    public UserModel getAdmin() {
        return UserModel.fromUser(userService.getAdmin());
    }


    @Override
    public UserModel getByUserName(final String userName) {
        Assert.hasText(userName, "User name should not be empty");
        final User user = userService.getUserByUsername(userName);
        return UserModel.fromUser(user);
    }

    @Override
    public UserModel getById(Long userId) {
        Assert.notNull(userId, "User id should not be empty");
        final User user = userService.getUserById(userId);
        return UserModel.fromUser(user);
    }

    // Utility methods
    private UserDto fromRegistrationRequest(final UserCreationRequestModel registrationRequestModel) {
        final UserDto userDto = new UserDto();
        userDto.setFirstName(registrationRequestModel.getFirstName());
        userDto.setLastName(registrationRequestModel.getLastName());
        return userDto;
    }

    private List<ErrorType> checkRequestModelForErrors(final UserCreationRequestModel registrationRequestModel) {
        return registrationRequestModel.checkForErrors();
    }

    private List<ErrorType> checkRequestModelForErrors(final UserUpdateRequestModel requestModel) {
        return requestModel.checkForErrors();
    }
}
