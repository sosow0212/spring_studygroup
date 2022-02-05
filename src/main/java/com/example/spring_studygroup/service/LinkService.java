package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.Link.LinkRepository;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public Link findByUser(User user) {
        return linkRepository.findByUser(user);
    }

    public Link findByTeam(Team team) {
        return linkRepository.findByTeam(team);
    }
}
