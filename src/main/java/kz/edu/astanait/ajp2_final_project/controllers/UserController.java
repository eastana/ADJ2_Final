package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {

        // call delete user method for ADMIN
        this.userService.deleteUserById(id);
        return "redirect:/admin";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        // save user to database
        userService.register(user);
        return "redirect:/admin";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get user from the service
        User user = userService.getUserById(id);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user";
    }
    @GetMapping("/admin")
    public String viewList(Model model){
        model.addAttribute("listUsers",userService.getAllUsers());
        return "admin";
    }
    @GetMapping("/profile")
    public String viewProfile(Model model){
        model.addAttribute("listUsers",userService.getAllUsers());
        return "profile";
    }
    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

}
