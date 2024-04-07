package project.aiport.aiportproject1.DAO;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.aiport.aiportproject1.Configuration.MailService;
import project.aiport.aiportproject1.Entity.users;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private MailService mailService;
    public String registerUser(String username, String password, String name, String surname,  String email ,String phone_number) {
        users user = new users();
        user.setUsername(username);
        user.setPassword("{noop}"+password);
        user.setName(name);
        user.setSurname(surname);
        user.setPhone_number(phone_number);
        user.setEmail(email);
        user.setToken(UUID.randomUUID().toString());
        user.setEnabled(0);
        System.out.println(user);
        userRepository.save(user);
        try {
            System.out.println("Wysłany mail");
            sendMail(user.getToken(), email);

        } catch (MessagingException e) {
            System.out.println("Problem z wysłaniem");
        }

        return "redirect::/showMyLoginPage";
    }

    public String sendMail(String token, String recipientEmail) throws MessagingException {
        mailService.sendVerificationTokenMail(recipientEmail,
                "Potwierdzenie rejestracji",
                "http://localhost:8080/confirm/", token);
        return "wysłano";
    }

    public boolean verifyUser(String token) {
        users user = userRepository.findByToken(token);

        if (user != null && user.getEnabled() == 0) {
            user.setEnabled(1);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}