package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewList(Model model){
        model.addAttribute("listUsers",userService.getAllUsers());
        return "admin";
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

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }
}
