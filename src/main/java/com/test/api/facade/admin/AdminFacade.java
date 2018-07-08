package com.test.api.facade.admin;

import com.test.api.model.request.admin.UserCreationRequestModel;
import com.test.api.model.response.user.UserModel;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 7:27 PM
 */
public interface AdminFacade {

    UserModel create(final UserCreationRequestModel registrationFormRequestModel);

    void remove(final Long userId);

    List<UserModel> getUsers();

    UserModel getByUserName(final String userName);
}
