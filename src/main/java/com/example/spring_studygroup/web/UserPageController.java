package com.example.spring_studygroup.web;

import com.example.spring_studygroup.config.auth.PrincipalDetails;
import com.example.spring_studygroup.domain.user.User;
import com.example.spring_studygroup.service.AuthService;
import com.example.spring_studygroup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequiredArgsConstructor
@Controller
public class UserPageController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/user/{userId}/edit")
    public String editUser(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("userId") Integer userId, Model model) {
        if(userId != principalDetails.getUser().getId()) {
            System.out.println("오류");
            return "redirect:/main";
        }


        model.addAttribute("user", principalDetails.getUser());
        return "/main/editUserPage";
    }

    @PostMapping("/user/{userId}/edit/process")
    public String editUserProcess(@PathVariable("userId") Integer userId ,User user, MultipartFile file) throws Exception {

        authService.editUser(userId, user, file);
        return "redirect:/main";
    }
}
