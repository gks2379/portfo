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
    @GetMapping("/contact")
    public String contactPage() { return "contact"; }
    @PostMapping("/contact/send")
    public String sendMail(ContactDto contactDto) {
        mailService.sendContactMail(contactDto);
        return "redirect:/";
    }
}