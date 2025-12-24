package com.hans.portfolio.controller;

import com.hans.portfolio.dto.ContactDto;
import com.hans.portfolio.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final MailService mailService;

    // 문의하기 페이지 이동
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    // 메일 전송 요청 처리
    @PostMapping("/contact/send")
    public String sendMail(ContactDto contactDto) {
        mailService.sendContactMail(contactDto);
        // 전송 완료 후 메인 페이지로 리다이렉트 (실제로는 완료 메시지를 띄우는게 좋지만 일단 홈으로!)
        return "redirect:/";
    }
}
