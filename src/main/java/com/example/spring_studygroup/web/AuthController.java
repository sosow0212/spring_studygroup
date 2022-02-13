package com.example.spring_studygroup.web;

import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.team.TeamRepository;
import com.example.spring_studygroup.domain.user.User;
import com.example.spring_studygroup.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;
    private final TeamRepository teamRepository;

    @GetMapping("/signin")
    public String SigninForm() {
        return "signin";
    }

    @GetMapping("/signup")
    public String SignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user, String teamName, MultipartFile file) throws Exception {
        // User에 signupDto 넣음
        authService.signup(user, teamName, file);
        return "signin";
    }
}

