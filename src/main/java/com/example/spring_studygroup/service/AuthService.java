package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.Link.LinkRepository;
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
            teamRepository.save(team);
            link.setTeam(team);
        } else {
            link.setTeam(teamRepository.findByName(teamName));
        }

        linkRepository.save(link);
        User userEntity = userRepository.save(user);
    }
}

