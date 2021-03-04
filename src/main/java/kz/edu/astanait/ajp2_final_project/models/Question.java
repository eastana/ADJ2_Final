package kz.edu.astanait.ajp2_final_project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "QuestionEntity")
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question")
    private String question;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", nullable = false, insertable = false, updatable = false)
    private List<Answer> answers;


}
