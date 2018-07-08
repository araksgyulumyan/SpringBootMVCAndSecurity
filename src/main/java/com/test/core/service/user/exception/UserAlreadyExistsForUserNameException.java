package com.test.core.service.user.exception;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 3:04 PM
 */
public class UserAlreadyExistsForUserNameException extends RuntimeException {
    private static final long serialVersionUID = -8694423320820730848L;

    // Properties
    private final String userName;

    public UserAlreadyExistsForUserNameException(final String userName) {
        super(String.format("User with name %s already exists", userName));
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
