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
        
        // 1. 받는 사람 (본인 이메일 주입받은 값 사용)
        message.setTo(myEmail);
        message.setSubject("[Portfolio Contact] " + contactDto.getName() + "님으로부터 메시지가 도착했습니다.");
        
        // 2. 메일 본문 구성
        String content = "보낸 사람: " + contactDto.getName() + "\n" +
                         "보낸 사람 이메일: " + contactDto.getEmail() + "\n\n" +
                         "내용:\n" + contactDto.getMessage();
        
        message.setText(content);
        
        // 3. 실제 발송
        mailSender.send(message);
    }
}

