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

        // null 값이 아니라면 기존 기술스택은 싹 다 지우고 새로 저장함
        List<Tech> techs = techRepository.findAllByTeam(team);
        for(Tech tech : techs) {
            techRepository.delete(tech);
        }


        // 받아온 값을 새로 저장함
        for(String c : checkedValue) {
            Tech tech = new Tech();
            tech.setTeam(team);
            tech.setName(c);
            techRepository.save(tech);
            System.out.println(tech.getName());
        }
    }


    public List<Tech> loadTechs(Team team) {
        List<Tech> techs = techRepository.findAllByTeam(team);
        return techs;
    }
}
