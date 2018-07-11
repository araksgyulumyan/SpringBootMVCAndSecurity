package com.test.api.controller.admin;

import com.test.api.facade.admin.AdminFacade;
import com.test.api.model.DashboardModel;
import com.test.api.model.request.admin.UserCreationRequestModel;
import com.test.api.model.request.admin.UserUpdateRequestModel;
import com.test.api.model.response.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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
    public ModelAndView dashboard(/*final Principal principal*/) {
//        final UserModel adminModel = adminFacade.getByUserName(principal.getName());
        final List<UserModel> userModels = adminFacade.getUsers();
        final UserModel adminModel = adminFacade.getAdmin();
        final DashboardModel dashboardModel =
                new DashboardModel(new HashSet<>(userModels), adminModel);
        final ModelAndView modelAndView = new ModelAndView("admindashboard");
        modelAndView.addObject("dashboardModel", dashboardModel);
        return modelAndView;
    }

    @GetMapping("/create_user")
    public ModelAndView getAddUserView() {
        ModelAndView modelAndView = new ModelAndView("adduser");
        modelAndView.addObject("userCreationRequestModel", new UserCreationRequestModel());
        return modelAndView;
    }

    @PostMapping(value = "/create_user",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView createUser(@Valid final UserCreationRequestModel requestModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("adduser");
        }

        UserModel savedUser = adminFacade.create(requestModel);
        return new ModelAndView("redirect:/admin/users/" + savedUser.getUserName());
    }

    @GetMapping("/update_user/{id}")
    public ModelAndView getUpdateUserView(@PathVariable("id") final Long userId) {
        ModelAndView modelAndView = new ModelAndView("updateuser");
        modelAndView.addObject("userUpdateRequestModel", adminFacade.getById(userId));
        return modelAndView;
    }

    @PutMapping(value = "/update_user/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updateUser(@PathVariable("id") final Long userId, @Valid final UserUpdateRequestModel requestModel,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("updateuser");
        }
        UserModel updatedUser = adminFacade.update(userId, requestModel);
        return new ModelAndView("redirect:/admin/users/" + updatedUser.getUserName());
    }


    @GetMapping(value = "/users/{username}")
    public ModelAndView getSingleUserView(@PathVariable("username") final String userName) {
        ModelAndView modelAndView = new ModelAndView("singleuser");
        modelAndView.addObject("createdUserModel", adminFacade.getByUserName(userName));
        return modelAndView;
    }

    @GetMapping(value = "/delete_user/{id}")
    public ModelAndView deleteUser(@PathVariable("id") final Long userId) {
        adminFacade.remove(userId);
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers() {
        final List<UserModel> users = adminFacade.getUsers();
        return ResponseEntity.ok(users);
    }
}
