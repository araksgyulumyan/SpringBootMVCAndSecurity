package com.test.api.controller.admin;

import com.test.api.facade.admin.AdminFacade;
import com.test.api.model.DashboardModel;
import com.test.api.model.request.admin.UserCreationRequestModel;
import com.test.api.model.response.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 7:26 PM
 */
@Controller
@RolesAllowed("ROLE_ADMIN")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminFacade adminFacade;

    @GetMapping("/dashboard")
    public ModelAndView dashboard(final Principal principal) {
        final UserModel adminModel = adminFacade.getByUserName(principal.getName());
        //// TODO: 7/8/18
//        final List<UserModel> userModels = adminFacade.getUsers();
        final List<UserModel> userModels = loadDummyModels();
        final DashboardModel dashboardModel =
                new DashboardModel(adminModel.getUserName(), new HashSet<>(userModels));
        final ModelAndView modelAndView = new ModelAndView("admindashboard");
        modelAndView.addObject("dashboardModel", dashboardModel);
        return modelAndView;
    }

    @RequestMapping(value = "/create_user", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody final UserCreationRequestModel requestModel) {
        return ResponseEntity.ok(adminFacade.create(requestModel));
    }

    @RequestMapping(value = "/delete_user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") final Long userId) {
        adminFacade.remove(userId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers() {
        final List<UserModel> users = adminFacade.getUsers();
        return ResponseEntity.ok(users);
    }


    private List<UserModel> loadDummyModels() {
        final List<UserModel> userModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final UserModel userModel =
                    new UserModel((long) i, "userName" + i, "fname" + i, "lname" + i);
            userModels.add(userModel);
        }
        return userModels;
    }
}
