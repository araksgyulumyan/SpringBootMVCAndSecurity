package com.test.core.service.user.exception;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 3:10 PM
 */
public class UserNotExistsForUserNameException extends RuntimeException {
    private static final long serialVersionUID = 7278364817631221430L;

    private final String userName;

    public UserNotExistsForUserNameException(final String userName) {
        super(String.format("User with user name '%s' not exists", userName));
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
