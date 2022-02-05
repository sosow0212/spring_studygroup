package com.example.spring_studygroup.domain.todo;

import com.example.spring_studygroup.domain.team.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content; // 투부 내용

    private String state; // 미완료, 완료, 삭제

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private LocalDateTime createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
