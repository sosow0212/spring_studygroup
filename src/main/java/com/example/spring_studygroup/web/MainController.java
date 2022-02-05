package com.example.spring_studygroup.web;

import com.example.spring_studygroup.config.auth.PrincipalDetails;
import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.introStudy.IntroStudy;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.todo.Todo;
import com.example.spring_studygroup.domain.user.User;
import com.example.spring_studygroup.service.IntroStudyService;
import com.example.spring_studygroup.service.LinkService;
import com.example.spring_studygroup.service.TeamService;
import com.example.spring_studygroup.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final LinkService linkService;
    private final TeamService teamService;
    private final IntroStudyService introStudyService;
    private final TodoService todoService;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        Link link = linkService.findByUser(principalDetails.getUser());
        Team team = link.getTeam();
        User user = principalDetails.getUser();
        List<Todo> todos = todoService.findAllTodo(team.getId());

        model.addAttribute("link", link);
        model.addAttribute("user", user);
        model.addAttribute("team", team);
        model.addAttribute("todos", todos);
        return "/main/main";
    }



    // 팀소개 수정
    @GetMapping("/main/intro/{teamId}/edit")
    public String introStudyEdit(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, @PathVariable("teamId") Integer teamId) {

        Team team = teamService.findById(teamId);

        model.addAttribute("team", team);
        model.addAttribute("user", principalDetails.getUser());
        return "/main/introStudyEditPage";
    }

    @PostMapping("/main/intro/{teamId}/edit")
    public String introStudyEditProcess(@PathVariable("teamId") Integer teamId , IntroStudy introStudy) {
        introStudyService.saveIntro(teamId, introStudy);
        return "redirect:/main";
    }


    @PostMapping("/main/todo/{teamId}")
    public String makeTodo(@PathVariable("teamId") Integer teamId, String content) {
        System.out.println(content);
        todoService.saveTodo(content, teamService.findById(teamId));
        return "redirect:/main#education";
    }

}
