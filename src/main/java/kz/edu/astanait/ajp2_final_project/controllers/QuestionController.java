package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.dto.VoteDto;
import kz.edu.astanait.ajp2_final_project.models.Question;
import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/add/question")
    public ModelAndView showQuestionAddPage() {
        ModelAndView mav = new ModelAndView("addQuestion");
        mav.addObject("addQuestionForm", new Question());
        return mav;
    }

    @PostMapping("/add/question")
    public String addQuestion(@ModelAttribute("addQuestion") Question questionForm){
        questionService.addQuestion(questionForm);
        return "addAnswer";
    }

}
