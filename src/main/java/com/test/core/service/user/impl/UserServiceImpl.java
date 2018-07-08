package com.test.core.service.user.impl;

import com.test.core.entity.user.User;
import com.test.core.repository.user.UserRepository;
import com.test.core.service.user.UserService;
import com.test.core.service.user.common.UserRole;
import com.test.core.service.user.dto.UserDto;
import com.test.core.service.user.exception.UserAlreadyExistsForUserNameException;
import com.test.core.service.user.exception.UserNotExistsForUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/2/18
 * Time - 8:34 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(final String userName, final String password,
                           final UserRole userRole, final UserDto userDto) {
        assertUserDto(userDto);
        final boolean existsForUserName = checkIfUserExistsForUserName(userName);
        if (existsForUserName) {
            throw new UserAlreadyExistsForUserNameException(userName);
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserRole(userRole);
        user.setPassword(passwordEncoder.encode(password));
        userDto.updateDomainModelProperties(user);
        user = userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(final Long userId, final UserDto userDto) {
        assertUserId(userId);
        assertUserDto(userDto);
        User user = getUserById(userId);
        userDto.updateDomainModelProperties(user);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        assertUserId(userId);
        final User user = userRepository.getOne(userId);
        assertUserNotNullForId(userId, user);
        return user;
    }

    @Override
    public User getUserByUsername(final String userName) {
        assertUserName(userName);
        final User user = userRepository.findByUserName(userName);
        assertUserNotNullForUserName(userName, user);
        return user;
    }

    @Override
    @Transactional
    public void removeUserById(final Long userId) {
        assertUserId(userId);
        final User user = getUserById(userId);
        userRepository.delete(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAllByUserRole(UserRole.USER);
    }

    // Utility methods
    private static void assertUserDto(final UserDto userDto) {
        Assert.notNull(userDto, "User dto should not be null");
        Assert.hasText(userDto.getFirstName(), "User dto first name should not be null");
        Assert.hasText(userDto.getLastName(), "User dto last name should not be null");
    }

    private static void assertUserName(final String userName) {
        Assert.hasText(userName, "User name should not be null");
    }

    private static void assertUserId(final Long userId) {
        Assert.notNull(userId, "User Id should not be null");
    }

    private void assertUserNotNullForId(final Long userId, final User user) {
        if (user == null) {
            throw new RuntimeException(String.format("User with id '%d' not exists", userId));
        }
    }

    private void assertUserNotNullForUserName(final String userName, final User user) {
        if (user == null) {
            throw new UserNotExistsForUserNameException(userName);
        }
    }

    private boolean checkIfUserExistsForUserName(final String userName) {
        return userRepository.findByUserName(userName) != null;
    }
}
