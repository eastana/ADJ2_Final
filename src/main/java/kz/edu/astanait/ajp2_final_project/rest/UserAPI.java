package kz.edu.astanait.ajp2_final_project.rest;

import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getByUsername/{username}")
    public @ResponseBody
    User getUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

}
