package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.award.Award;
import com.example.spring_studygroup.domain.award.AwardRepository;
import com.example.spring_studygroup.domain.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AwardService {
    private final AwardRepository awardRepository;

    public List<Award> findAllByTeam(Team team) {
        return awardRepository.findAllByTeam(team);
    }

    public void saveAward(String award, Team team) {
        Award newAward = new Award();
        newAward.setAward(award);
        newAward.setTeam(team);
        awardRepository.save(newAward);
    }

}
