package com.example.spring_studygroup.web;

import com.example.spring_studygroup.config.auth.PrincipalDetails;
import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.award.Award;
import com.example.spring_studygroup.domain.board.Board;
import com.example.spring_studygroup.domain.comment.Comment;
import com.example.spring_studygroup.domain.introStudy.IntroStudy;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.tech.Tech;
import com.example.spring_studygroup.domain.todo.Todo;
import com.example.spring_studygroup.domain.user.User;
import com.example.spring_studygroup.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;
    private final LinkService linkService;
    private final TeamService teamService;
    private final IntroStudyService introStudyService;
    private final TodoService todoService;
    private final TechService techService;
    private final BoardService boardService;
    private final AwardService awardService;
    private final CommentService commentService;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        Link link = linkService.findByUser(principalDetails.getUser());
        Team team = link.getTeam();
        User user = principalDetails.getUser();
        List<Todo> todos = todoService.findAllTodo(team.getId());
        List<Tech> techs = techService.loadTechs(team);
        List<Board> boards = boardService.findAllBoardByTeam(team);
        List<Award> awards = awardService.findAllByTeam(team);

        model.addAttribute("link", link);
        model.addAttribute("user", user);
        model.addAttribute("team", team);
        model.addAttribute("todos", todos);
        model.addAttribute("techs", techs);
        model.addAttribute("boards", boards);
        model.addAttribute("awards", awards);

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
    public String introStudyEditProcess(@PathVariable("teamId") Integer teamId, IntroStudy introStudy) {
        introStudyService.saveIntro(teamId, introStudy);
        return "redirect:/main";
    }


    // 투두리스트 처리
    @PostMapping("/main/todo/{teamId}")
    public String makeTodo(@PathVariable("teamId") Integer teamId, String content) {
        System.out.println(content);
        todoService.saveTodo(content, teamService.findById(teamId));
        return "redirect:/main#todo";
    }

    // 투두리스트 완료
    @GetMapping("/main/todo/done/{teamId}/{todoId}")
    public String doneTodo(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("teamId") Integer teamId, @PathVariable("todoId") Integer todoId) {
        User user = principalDetails.getUser();
        Team team = teamService.findById(teamId);
        if (linkService.findByUser(user).getTeam() != team) {
            return "redirect:/main#todo";
        }
        todoService.doneTodo(todoId);
        return "redirect:/main#todo";
    }

    @GetMapping("/main/todo/delete/{teamId}/{todoId}")
    public String deleteTodo(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("teamId") Integer teamId, @PathVariable("todoId") Integer todoId) {
        User user = principalDetails.getUser();
        Team team = teamService.findById(teamId);
        if (linkService.findByUser(user).getTeam() != team) {
            return "redirect:/main#todo";
        }
        todoService.deleteTodo(todoId);
        return "redirect:/main#todo";
    }

    // 기술스택 추가하기
    @GetMapping("/main/stack/{teamId}")
    public String addStack(@PathVariable("teamId") Integer teamId, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        Team team = teamService.findById(teamId);
        model.addAttribute("team", team);
        model.addAttribute("user", principalDetails.getUser());
        return "/main/addTechStack";
    }

    // 기술스택 추가
    @PostMapping("/main/stack/{teamId}")
    public String addStackProcess(@PathVariable("teamId") Integer teamId, @RequestParam List<String> checkedValue) {

        Team team = teamService.findById(teamId);
        techService.saveTech(checkedValue, team);
        return "redirect:/main#skills";
    }






    // 게시글 업로드 페이지
    @GetMapping("/main/board/write/{teamId}")
    public String boardWritePage(@PathVariable("teamId") Integer teamId, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        User user = principalDetails.getUser();
        Team team = teamService.findById(teamId);
        model.addAttribute("user", user);
        model.addAttribute("team", team);
        return "/main/boardUploadPage";
    }

    @PostMapping("/main/board/write/{teamId}")
    public String boardWriteProcess(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Integer teamId, Board board) {
        User user = principalDetails.getUser();
        Team team = teamService.findById(teamId);
        boardService.writeBoard(board, team, user);
        return "redirect:/main#board";
    }

    // 게시글 수정
    @GetMapping("/main/board/edit/{teamId}/{boardId}")
    public String boardEditPage(@PathVariable("teamId") Integer teamId, @PathVariable("boardId") Integer boardId, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        User user = principalDetails.getUser();
        Board board = boardService.findBoardById(boardId);
        Team team = teamService.findById(teamId);

        model.addAttribute("board", board);
        model.addAttribute("team", team);
        model.addAttribute("user", user);
        return "main/boardEditPage";
    }

    @PostMapping("/main/board/edit/{teamId}/{boardId}")
    public String boardEditPage(@PathVariable("teamId") Integer teamId, @PathVariable("boardId") Integer boardId, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model, Board board) {
        boardService.editBoard(boardId, board);
        return "redirect:/main#board";
    }

    // 게시글 삭제
    @GetMapping("/main/board/edit/{boardId}")
    public String boardDelete(@PathVariable("boardId") Integer boardId) {
        Board board = boardService.findBoardById(boardId);
        boardService.boardDelete(board);
        return "redirect:/main#board";
    }



    // 게시글 댓글 comment add
    @PostMapping("/main/board/{boardId}/comment/{userId}")
    public String addComment(@PathVariable("boardId") Integer boardId, @PathVariable("userId") Integer userId, String text) {
        Board board = boardService.findBoardById(boardId);
        User user = userService.findById(userId);

        commentService.saveComment(user, board, text);
        return "redirect:/main#board";
    }



    // Award 추가
    @PostMapping("/main/{teamId}/award/add")
    public String awardAdd(@PathVariable("teamId") Integer teamId , String award) {
        Team team = teamService.findById(teamId);
        awardService.saveAward(award, team);

        return "redirect:/main#awards";
    }

    @GetMapping("/main/{teamId}/award/{awardId}/delete")
    public String awardDelete(@PathVariable("teamId") Integer teamId, @PathVariable("awardId") Integer awardId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Team team = teamService.findById(teamId);
        User user = principalDetails.getUser();

        Link userLink = linkService.findByUser(user);
        if(userLink.getTeam() != team) {
            return "redirect:/main";
        }

        Award award = awardService.findByAwardId(awardId);
        awardService.deleteAward(award);
        return "redirect:/main#awards";
    }
}

