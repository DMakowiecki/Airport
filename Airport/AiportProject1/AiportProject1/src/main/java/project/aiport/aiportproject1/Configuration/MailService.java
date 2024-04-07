package project.aiport.aiportproject1.Configuration;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendVerificationTokenMail(String to,
                         String subject,
                         String text, String token
                         )  {
        SimpleMailMessage message= new SimpleMailMessage();
        System.out.println("Mail został wysłany na adres "+ to + "a token to" + token);
        message.setFrom("aiportapp@onet.pl");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text+token);
        javaMailSender.send(message);

    }
}