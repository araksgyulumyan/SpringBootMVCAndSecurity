package com.test.api.model;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 9:17 PM
 */
public class LoginModel implements Serializable {

    private static final long serialVersionUID = 5629340445946865517L;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public LoginModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
