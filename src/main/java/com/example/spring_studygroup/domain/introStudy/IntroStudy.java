package com.example.spring_studygroup.domain.introStudy;

import com.example.spring_studygroup.domain.team.Team;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}

