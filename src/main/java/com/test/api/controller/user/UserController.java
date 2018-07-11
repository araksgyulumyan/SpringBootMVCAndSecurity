package com.test.api.controller.user;

import com.test.api.facade.user.UserFacade;
import com.test.api.model.response.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 4:21 PM
 */

@Controller
@RolesAllowed("ROLE_USER")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/user/profile")
    public ModelAndView profile(final Principal principal) {
        final UserModel userResponseModel = userFacade.getByUserName(principal.getName());
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("userModel", userResponseModel);
        return modelAndView;
    }

//    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT,
//            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity updateUser(@PathVariable("id") final Long userId, @RequestBody final UserUpdateRequestModel requestModel) {
//        return ResponseEntity.ok(userFacade.update(userId, requestModel));
//    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable("id") final Long userId) {
        return ResponseEntity.ok(userFacade.getById(userId));
    }
}
