package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.Answer;
import kz.edu.astanait.ajp2_final_project.models.Question;
import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.models.Vote;
import kz.edu.astanait.ajp2_final_project.services.AnswerService;
import kz.edu.astanait.ajp2_final_project.services.QuestionService;
import kz.edu.astanait.ajp2_final_project.services.UserService;
import kz.edu.astanait.ajp2_final_project.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private AnswerService answerService;

    @GetMapping(value = {"", "/"})
    public String viewList(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("listQuestions", questionService.getAll());
        return "admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {

        // call delete user method for ADMIN
        this.userService.deleteUserById(id);
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
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @GetMapping("/showStatistics")
    public String showStatistics(Model model) {

        return "statistics";
    }
}
