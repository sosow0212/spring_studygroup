package com.example.spring_studygroup.web;

import com.example.spring_studygroup.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {


    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        model.addAttribute("user", principalDetails.getUser());
        return "/main/main";
    }
}
