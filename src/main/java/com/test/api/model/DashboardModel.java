package com.test.api.model;

import com.test.api.model.response.user.UserModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 9:42 PM
 */
public class DashboardModel implements Serializable {
    private static final long serialVersionUID = -8498011053589524883L;

    // Properties

    private Set<UserModel> users;

    private UserModel admin;

    // Constructors
    public DashboardModel() {
    }

    public DashboardModel(Set<UserModel> users, UserModel admin) {
        this.users = users;
        this.admin = admin;
    }


    // Properties getters and setters

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        if (users == null) {
            users = new HashSet<>();
        }
        this.users = users;
    }

    public UserModel getAdmin() {
        return admin;
    }

    public void setAdmin(UserModel admin) {
        this.admin = admin;
    }

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
        DashboardModel rhs = (DashboardModel) obj;
        return new EqualsBuilder()
                .append(this.users, rhs.users)
                .append(this.admin, rhs.admin)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(users)
                .append(admin)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("users", users)
                .append("admin", admin)
                .toString();
    }
}
