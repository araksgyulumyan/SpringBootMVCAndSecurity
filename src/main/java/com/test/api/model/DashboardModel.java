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
    private String adminUserName;

    private Set<UserModel> users;

    // Constructors
    public DashboardModel() {
    }

    public DashboardModel(final String adminUserName, final Set<UserModel> users) {
        this.adminUserName = adminUserName;
        this.users = users;
    }

    // Properties getters and setters
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        if(users == null) {
            users = new HashSet<>();
        }
        this.users = users;
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
                .append(this.getAdminUserName(), rhs.getAdminUserName())
                .append(this.getUsers().size(), rhs.getUsers().size())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getAdminUserName())
                .append(getUsers().size())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("adminUserName", getAdminUserName())
                .append("users", getUsers().size())
                .toString();
    }
}
