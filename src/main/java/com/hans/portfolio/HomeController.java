package com.hans.portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "안녕하세요! 제 포트폴리오 사이트에 오신 것을 환영합니다.");
        return "index";
    }
}
