package com.test.api.model.request.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 4:24 PM
 */

public class LoginRequestModel implements Serializable {

    private static final long serialVersionUID = 4503771044182581449L;

    // Properties
    private String userName;

    private String password;

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Equals and Hashcode
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        LoginRequestModel rhs = (LoginRequestModel) obj;
        return new EqualsBuilder()
                .append(this.userName, rhs.userName)
                .append(this.password, rhs.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userName)
                .append(password)
                .toHashCode();
    }

    // ToString
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("password", password)
                .toString();
    }
}
