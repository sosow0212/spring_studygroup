package com.example.spring_studygroup.web;

import com.example.spring_studygroup.config.auth.PrincipalDetails;
import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.service.LinkService;
import com.example.spring_studygroup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final LinkService linkService;
    private final TeamService teamService;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        Link link = linkService.findByUser(principalDetails.getUser());
        model.addAttribute("link", link);
        model.addAttribute("user", principalDetails.getUser());
        return "/main/main";
    }



    // 팀소개 수정
    @GetMapping("/main/intro/{teamId}/edit")
    public String introStudyView(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, @PathVariable("teamId") Integer teamId) {

        Team team = teamService.findById(teamId);

        model.addAttribute("team", team);
        model.addAttribute("user", principalDetails.getUser());
        return "introStudyEditPage";
    }
}
