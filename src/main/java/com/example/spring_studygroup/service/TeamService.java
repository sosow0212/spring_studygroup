package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.team.TeamRepository;
import com.example.spring_studygroup.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }

    public Team findById(int id) {
        return teamRepository.findById(id);
    }
}
