package kz.edu.astanait.ajp2_final_project.dto;

import kz.edu.astanait.ajp2_final_project.models.Answer;
import kz.edu.astanait.ajp2_final_project.models.Question;
import lombok.Data;

@Data
public class VoteDto {
    private Question questionDto;
    private Answer answerDto;
}
