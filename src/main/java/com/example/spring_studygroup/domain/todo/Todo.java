package com.example.spring_studygroup.domain.todo;

import com.example.spring_studygroup.domain.team.Team;
import lombok.*;

import javax.persistence.*;


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
}
