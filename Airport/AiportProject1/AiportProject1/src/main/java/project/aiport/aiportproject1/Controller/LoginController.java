package project.aiport.aiportproject1.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import project.aiport.aiportproject1.DAO.PassengersRepository;
import project.aiport.aiportproject1.DAO.UserService;
import project.aiport.aiportproject1.DAO.UsersRepository;

import project.aiport.aiportproject1.Entity.users;

@Controller
public class LoginController {
    
    @Autowired
    private HttpSession httpSession;
    private PassengersRepository PassengersRepository;
    private UsersRepository usersRepository;
    @Autowired
    UserService userService;
    public LoginController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){

        return "login.html";
    }
    @GetMapping("/showMyLoginPage?error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login-page";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam("registerName") String name,
                               @RequestParam("registerSurname") String surname,
                               @RequestParam("registerUsername") String username,
                               @RequestParam("registerEmail") String email,
                               @RequestParam("registerPhone_number") String phoneNumber,
                               @RequestParam("registerPassword") String password,
                               @RequestParam("registerRepeatPassword") String repeatPassword,
                               Model model) {
        userService.registerUser(username,password,name,surname,email,phoneNumber);
        return "redirect::/showMyLoginPage";
    }


    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, Model model) {

        httpSession.setAttribute("username", "exampleUser");
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        httpSession.invalidate();
        return "redirect:/showMyLoginPage";
    }

}



