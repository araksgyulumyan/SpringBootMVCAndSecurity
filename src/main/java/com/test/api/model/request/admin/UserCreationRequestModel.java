package com.test.api.model.request.admin;

import com.test.api.model.response.common.ErrorType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 4:23 PM
 */
public class UserCreationRequestModel implements Serializable {

    private static final long serialVersionUID = -7050236540495696778L;

    // Properties
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    // Getters and Setters
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ErrorType> checkForErrors() {
        final List<ErrorType> errorTypes = new ArrayList<>();
        if(StringUtils.isEmpty(getUserName())) {
            errorTypes.add(ErrorType.INVALID_USERNAME);
        }
        if(StringUtils.isEmpty(getFirstName())) {
            errorTypes.add(ErrorType.INVALID_FIRST_NAME);
        }
        if(StringUtils.isEmpty(getLastName())) {
            errorTypes.add(ErrorType.INVALID_LAST_NAME);
        }
        if(StringUtils.isEmpty(getPassword())) {
            errorTypes.add(ErrorType.INVALID_PASSWORD);
        }
        return errorTypes;
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
        UserCreationRequestModel rhs = (UserCreationRequestModel) obj;
        return new EqualsBuilder()
                .append(this.userName, rhs.userName)
                .append(this.password, rhs.password)
                .append(this.firstName, rhs.firstName)
                .append(this.lastName, rhs.lastName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userName)
                .append(password)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }

    // ToString
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("password", password)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
