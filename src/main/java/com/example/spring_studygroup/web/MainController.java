package com.example.spring_studygroup.web;

import com.example.spring_studygroup.config.auth.PrincipalDetails;
import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final LinkService linkService;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        Link link = linkService.findByUser(principalDetails.getUser());
        model.addAttribute("link", link);
        model.addAttribute("user", principalDetails.getUser());
        return "/main/main";
    }
}
