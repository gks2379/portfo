package com.hans.portfolio.service;
import com.hans.portfolio.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String myEmail;
    public void sendContactMail(ContactDto contactDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(myEmail);
        message.setSubject("[Portfolio Contact] " + contactDto.getName() + "님으로부터 메시지가 도착했습니다.");
        String content = "보낸 사람: " + contactDto.getName() + "\n" +                         "보낸 사람 이메일: " + contactDto.getEmail() + "\n\n" +                         "내용:\n" + contactDto.getMessage();
        message.setText(content);
        mailSender.send(message);
    }
}