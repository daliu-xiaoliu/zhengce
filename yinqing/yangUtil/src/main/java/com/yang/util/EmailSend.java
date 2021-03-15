package com.yang.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Random;

public class EmailSend {

    private JavaMailSender mailSender;

    private String emailFrom;

    private String emailText;

    private String emailTitle;

    private SimpleMailMessage message=new SimpleMailMessage();

    public EmailSend(JavaMailSender mailSender, String emailFrom, String emailText, String emailTitle) {
        this.mailSender = mailSender;
        this.emailFrom = emailFrom;
        this.emailText = emailText;
        this.emailTitle = emailTitle;
    }

    public String sendSimpleEmail(String code,String emailTo){
        System.out.println(emailFrom);
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        System.out.println(emailTo);
        message.setSubject(emailTitle);
        message.setText(emailText+code);
        System.out.println(emailTo);
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
