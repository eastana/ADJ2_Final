package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("userForm", new User());
        return mav;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm) {
        userService.register(userForm);
        return "login";
    }
    @GetMapping("/profile")
    public String viewProfile(HttpServletRequest httpServletRequest, Model model){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        model.addAttribute("userInfo",userService.getUserById(user.getId()));
        return "profile";
    }
    @GetMapping("/FormForUpdate/{id}")
    public String FormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get user from the service
        User user = userService.getUserById(id);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user1";
    }
    @PostMapping("/saveChange")
    public String saveUser(@ModelAttribute("user") User user) {
        // save user to database
        userService.register(user);
        return "redirect:/profile";
    }

}
