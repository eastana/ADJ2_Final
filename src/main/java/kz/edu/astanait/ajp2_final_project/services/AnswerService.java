package kz.edu.astanait.ajp2_final_project.services;

import kz.edu.astanait.ajp2_final_project.models.Answer;
import kz.edu.astanait.ajp2_final_project.models.Question;
import kz.edu.astanait.ajp2_final_project.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public void addAnswers(Answer answer, Question relationId){
        answer.setQuestion(relationId);
        answerRepository.save(answer);
    }
}
