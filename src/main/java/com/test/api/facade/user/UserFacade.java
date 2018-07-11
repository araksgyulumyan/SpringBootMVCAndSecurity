package com.test.api.facade.user;

import com.test.api.model.response.user.UserModel;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 7:10 PM
 */

public interface UserFacade {

//    UserModel update(final Long userId, final UserUpdateRequestModel requestModel);

    UserModel getById(Long userId);

    UserModel getByUserName(final String userName);
}
