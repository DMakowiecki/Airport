package project.aiport.aiportproject1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.aiport.aiportproject1.Configuration.MailService;
import project.aiport.aiportproject1.DAO.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private final MailService mailService;

    @Autowired
    public UserController(MailService mailService) {
        this.mailService = mailService;
    }


    @GetMapping("/confirm/{token}")
    public String confirm(@PathVariable String token) {
        if (userService.verifyUser(token)) {
            return "Dziękujemy! Twoje konto zostało zweryfikowane.";
        }
        return "Nieprawidłowy token lub konto zostało już zweryfikowane.";
    }
}