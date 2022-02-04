package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.Link.LinkRepository;
import com.example.spring_studygroup.domain.introStudy.IntroStudy;
import com.example.spring_studygroup.domain.introStudy.IntroStudyRepository;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.team.TeamRepository;
import com.example.spring_studygroup.domain.user.User;
import com.example.spring_studygroup.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TeamRepository teamRepository;
    private final LinkRepository linkRepository;
    private final IntroStudyRepository introStudyRepository;

    @Transactional // Write(Insert, Update, Delete)
    public void signup(User user, String teamName) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");

        Link link = new Link();
        link.setUser(user);

        if(teamRepository.findByName(teamName) == null) {
            Team team = new Team();
            team.setName(teamName);

            // 추후에 이곳에 게시판, 투두리스트를 팀에 저장해야함
            // team.setTodo(...)

            // introStudy 작성
            IntroStudy introStudy = new IntroStudy();
            introStudy.setProjectIntro("프로젝트 소개를 작성해주세요.");
            introStudy.setProjectTitle("프로젝트 제목을 작성해주세요.");
            introStudy.setTeamIntro("팀 소개를 작성해주세요.");
            introStudy.setTeamName(team.getName());
            introStudy.setTeam(team);
            introStudyRepository.save(introStudy);
            team.setIntroStudy(introStudy);
            // introStudy 생성 종료

            teamRepository.save(team);
            link.setTeam(team);
        } else {
            link.setTeam(teamRepository.findByName(teamName));
        }

        linkRepository.save(link);
        User userEntity = userRepository.save(user);
    }
}

