package com.example.spring_studygroup.domain.introStudy;

import com.example.spring_studygroup.domain.team.Team;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class IntroStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String teamName; // 팀이름

    private String teamIntro; // 팀소개

    private String projectTitle; // 프로젝트 제목

    private String projectIntro; // 프로젝트 소개

    @OneToOne(mappedBy = "introStudy")
    private Team team;
}
