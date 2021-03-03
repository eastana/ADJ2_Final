package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.Answer;
import kz.edu.astanait.ajp2_final_project.models.Question;
import kz.edu.astanait.ajp2_final_project.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @GetMapping("/add/answer")
    public ModelAndView showAnswerAddPage() {
        ModelAndView mav = new ModelAndView("addAnswer");
        mav.addObject("addAnswerForm", new Answer());
        return mav;
    }

    @PostMapping("/add/answer")
    public String addAnswer(@ModelAttribute("addAnswer") Answer answerForm) {
        answerService.addAnswers(answerForm);
        return "";
    }

}
