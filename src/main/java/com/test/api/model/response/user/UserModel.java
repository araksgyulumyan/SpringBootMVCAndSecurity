package com.test.api.model.response.user;

import com.test.api.model.response.AbstractModel;
import com.test.api.model.response.common.ErrorType;
import com.test.core.entity.user.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 7:01 PM
 */
public class UserModel extends AbstractModel {
    private static final long serialVersionUID = -929140590564485107L;

    // Properties
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    // Constructors
    public UserModel() {
    }

    public UserModel(Long id, String userName, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static UserModel fromUser(final User user) {
        final UserModel model = new UserModel();
        model.setId(user.getId());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setUserName(user.getUserName());
        return model;
    }

    public static UserModel withErrors(final List<ErrorType> errors) {
        UserModel model = new UserModel();
        model.addErrors(errors);
        return model;
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
        UserModel rhs = (UserModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.userName, rhs.userName)
                .append(this.firstName, rhs.firstName)
                .append(this.lastName, rhs.lastName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(userName)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("userName", userName)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
