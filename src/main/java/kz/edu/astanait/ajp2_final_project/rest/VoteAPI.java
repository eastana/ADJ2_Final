package kz.edu.astanait.ajp2_final_project.rest;

import kz.edu.astanait.ajp2_final_project.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteAPI {
    private final VoteService voteService;

    @Autowired
    public VoteAPI(VoteService voteService) {
        this.voteService = voteService;
    }
//
//    @PostMapping("/addVote")
//    public void addVote()
}