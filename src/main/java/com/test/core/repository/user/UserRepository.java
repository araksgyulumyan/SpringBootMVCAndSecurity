package com.test.core.repository.user;

import com.test.core.entity.user.User;
import com.test.core.service.user.common.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/2/18
 * Time - 8:28 PM
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds user for provided username
     *
     * @param userName
     * @return User or null
     */
    User findByUserName(final String userName);

    /**
     * Finds all users with provided user role
     *
     * @param userRole
     * @return list of users
     */
    List<User> findAllByUserRole(final UserRole userRole);
}
