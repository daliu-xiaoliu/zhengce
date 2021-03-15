package login.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailSend {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.fromUser}")
    private String emailFrom;

    @Value("${email.text}")
    private String emailText;

    @Value(("${email.title}"))
    private String emailTitle;

    private SimpleMailMessage message=new SimpleMailMessage();


    public String sendSimpleEmail(String code,String emailTo){

        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject(emailTitle);
        message.setText(emailText+code);
        mailSender.send(message);
        return code;
    }


    public String generateCode(){
        String msg="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder=new StringBuilder();
        Random r=new Random();
        for (int i = 0; i < 5; i++) {
            int x=r.nextInt(36);
            builder.append(msg.charAt(x));
        }
        return builder.toString();
    }


}
