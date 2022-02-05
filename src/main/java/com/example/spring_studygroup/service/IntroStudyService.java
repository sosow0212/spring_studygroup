package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.introStudy.IntroStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IntroStudyService {
    private final IntroStudyRepository introStudyRepository;


}
