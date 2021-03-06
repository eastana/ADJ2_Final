package kz.edu.astanait.ajp2_final_project.controllers;

import kz.edu.astanait.ajp2_final_project.models.Answer;
import kz.edu.astanait.ajp2_final_project.models.Question;
import kz.edu.astanait.ajp2_final_project.services.AnswerService;
import kz.edu.astanait.ajp2_final_project.services.QuestionService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VoteController {
    private QuestionService questionService;
    private AnswerService answerService;

    @Autowired
    public VoteController(QuestionService questionService, AnswerService answerService) {
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
    public String addQuestion(@ModelAttribute("addQuestionForm") Question questionForm) {
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
    public String addAnswer(@ModelAttribute("addAnswerForm") Answer answerForm) {
        answerService.addAnswers(answerForm);
        return "admin";
    }

    @GetMapping("/question/update/{id}")
    public String updateQuestion(@PathVariable(value = "id") long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("questionToEdit", question);
        return "updateQuestion";
    }

    @PostMapping("/question/update/")
    public String updateQuestion(HttpServletRequest request) {
        Question question = new Question();
        Answer answer = new Answer();

        answer.setAnswerId(Long.valueOf(request.getParameter("answerId")));
        answer.setAnswerOne(request.getParameter("answerOne"));
        answer.setAnswerTwo(request.getParameter("answerSecond"));
        answer.setAnswerThree(request.getParameter("answerThree"));
        answer.setAnswerFour(request.getParameter("answerFour"));

        question.setId(Long.valueOf(request.getParameter("questionId")));
        question.setQuestion(request.getParameter("questionName"));
        question.setAnswer(answer);

        questionService.updateQuestion(question);

        return "admin";
    }

    @GetMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable(value = "id") long id) {
        questionService.deleteQuestion(id);

        return "admin";
    }

}