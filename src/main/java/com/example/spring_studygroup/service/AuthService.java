package com.example.spring_studygroup.service;

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

    @Transactional // Write(Insert, Update, Delete)
    public void signup(User user, String teamName) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");

        if(teamRepository.findByName(teamName) == null) {
            Team team = new Team();
            team.setName(teamName);
            teamRepository.save(team);
        } // 널 값이면 팀을 만듬

        Team team = teamRepository.findByName(teamName);
        user.setTeam(team);

        User userEntity = userRepository.save(user);
    }
}

