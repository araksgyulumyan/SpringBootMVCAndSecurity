package com.test.core.service.user;

import com.test.core.entity.user.User;
import com.test.core.service.user.common.UserRole;
import com.test.core.service.user.dto.UserDto;
import com.test.core.service.user.exception.UserAlreadyExistsForUserNameException;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/2/18
 * Time - 8:34 PM
 */
public interface UserService {

    /**
     * Creates user
     *
     * @param userName
     * @param password
     * @param userRole
     * @param userDto
     * @return User
     * @throws UserAlreadyExistsForUserNameException if user with provided user name already exists
     */
    User createUser(final String userName, final String password,
                    final UserRole userRole, final UserDto userDto);

    /**
     * Updates user
     *
     * @param userId
     * @param userDto
     * @return user
     */
    User updateUser(final Long userId, final UserDto userDto);

    /**
     * Get user for provided ID
     *
     * @param userId
     * @return User
     * @throws RuntimeException
     */
    User getUserById(final Long userId);

    /**
     * Find user for provided user name
     *
     * @param userName
     * @return User
     */
    User getUserByUsername(final String userName);

    /**
     * Removes user for provided identifier
     *
     * @param userId
     */
    void removeUserById(final Long userId);

    /**
     * Get all users
     *
     * @return list of users
     */
    List<User> getUsers();

    User getAdmin();

    boolean checkIfAdminExists();
}
