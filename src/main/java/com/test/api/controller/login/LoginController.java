package com.test.api.controller.login;

import com.test.api.model.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by araksgyulumyan
 * Date - 7/8/18
 * Time - 8:36 PM
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/user")
    public ModelAndView getUserLoginView() {
        ModelAndView modelAndView = new ModelAndView("loginuser");
        modelAndView.addObject("loginModel", new LoginModel());
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView getAdminLoginView() {
        ModelAndView modelAndView = new ModelAndView("loginadmin");
        modelAndView.addObject("loginModel", new LoginModel());
        return modelAndView;
    }
}
