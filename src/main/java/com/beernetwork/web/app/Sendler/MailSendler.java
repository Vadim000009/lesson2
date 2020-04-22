package com.beernetwork.web.app.Sendler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class MailSendler {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String emailRegisteredNow) {
        System.out.println(emailRegisteredNow);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailRegisteredNow);
        msg.setSubject("Добро пожаловать. Снова");
        msg.setText("Не будь лапшой\n Бухай побольше");
        javaMailSender.send(msg);
    }

//    void sendEmailWithAttachment() throws MessagingException, IOException {
//
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        // true = многострочноре сообщение
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setTo("to_@email");
//
//        helper.setSubject("Testing from Spring Boot");
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
//        helper.setText("<h1>Check attachment for image!</h1>", true);
//
//        // hard coded a file path
//        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
//
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
//
//        javaMailSender.send(msg);
//
//    }
}
