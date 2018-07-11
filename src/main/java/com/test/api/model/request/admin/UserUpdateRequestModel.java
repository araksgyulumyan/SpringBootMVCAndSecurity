package com.test.api.model.request.admin;

import com.test.api.model.response.common.ErrorType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 3:34 PM
 */
public class UserUpdateRequestModel implements Serializable {
    private static final long serialVersionUID = -3233537617165516279L;

    // Properties
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public UserUpdateRequestModel() {
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
        if (StringUtils.isEmpty(getFirstName())) {
            errorTypes.add(ErrorType.INVALID_FIRST_NAME);
        }
        if (StringUtils.isEmpty(getLastName())) {
            errorTypes.add(ErrorType.INVALID_LAST_NAME);
        }
        return errorTypes;
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
        UserUpdateRequestModel rhs = (UserUpdateRequestModel) obj;
        return new EqualsBuilder()
                .append(this.firstName, rhs.firstName)
                .append(this.lastName, rhs.lastName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
