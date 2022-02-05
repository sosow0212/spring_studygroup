package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.introStudy.IntroStudy;
import com.example.spring_studygroup.domain.introStudy.IntroStudyRepository;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IntroStudyService {
    private final IntroStudyRepository introStudyRepository;
    private final TeamRepository teamRepository;

    public void saveIntro(int teamId, IntroStudy introStudy) {
        Team team = teamRepository.findById(teamId);
        IntroStudy orig = introStudyRepository.findById(team.getIntroStudy().getId());

        orig.setTeamIntro(introStudy.getTeamIntro());
        orig.setProjectTitle(introStudy.getProjectTitle());
        orig.setProjectIntro(introStudy.getProjectIntro());

        introStudyRepository.save(orig);
    }

}
