package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.Answer;
import kz.edu.astanait.ajp2_final_project.models.Question;
import kz.edu.astanait.ajp2_final_project.services.AnswerService;
import kz.edu.astanait.ajp2_final_project.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VoteController {
    private QuestionService questionService;
    private AnswerService answerService;
    private Question relationId;
    @Autowired
    public VoteController(QuestionService questionService, AnswerService answerService){
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/question/add")
    public ModelAndView showQuestionAddPage() {
        ModelAndView mav = new ModelAndView("addQuestion");
        mav.addObject("addQuestionForm", new Question());
        return mav;
    }

    @PostMapping("/question/add")
    public String addQuestion(@ModelAttribute("addQuestionForm") Question questionForm){
        relationId = questionForm;
        questionService.addQuestion(questionForm);
        return "redirect:/answer/add";
    }

    @GetMapping("/answer/add")
    public ModelAndView showAnswerAddPage() {
        ModelAndView mav = new ModelAndView("addAnswer");
        mav.addObject("addAnswerForm", new Answer());
        return mav;
    }

    @PostMapping("/answer/add")
    public String addAnswer(@ModelAttribute("addAnswerForm") Answer answerForm){
        answerService.addAnswers(answerForm, relationId);
        return "";
    }

}