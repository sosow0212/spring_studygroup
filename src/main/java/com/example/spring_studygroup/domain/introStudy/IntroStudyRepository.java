package com.example.spring_studygroup.domain.introStudy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroStudyRepository extends JpaRepository<IntroStudy, Integer> {
}
