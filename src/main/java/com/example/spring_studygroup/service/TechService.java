package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.tech.Tech;
import com.example.spring_studygroup.domain.tech.TechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TechService {
    private final TechRepository techRepository;

    public void saveTech(List<String> checkedValue, Team team) {
        if(checkedValue == null) {
            return;
        }

        for(String c : checkedValue) {
            Tech tech = new Tech();
            tech.setTeam(team);
            tech.setName(c);
            techRepository.save(tech);
            System.out.println(tech.getName());
        }
    }
}
