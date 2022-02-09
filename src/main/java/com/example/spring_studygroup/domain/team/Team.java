package com.example.spring_studygroup.domain.team;

import com.example.spring_studygroup.domain.Link.Link;
import com.example.spring_studygroup.domain.board.Board;
import com.example.spring_studygroup.domain.introStudy.IntroStudy;
import com.example.spring_studygroup.domain.tech.Tech;
import com.example.spring_studygroup.domain.todo.Todo;
import com.example.spring_studygroup.domain.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; // 팀이름

    @OneToOne
    @JoinColumn(name = "introStudy_id")
    private IntroStudy introStudy;


    @OneToMany(mappedBy = "team")
    private List<Link> users = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Tech> techs = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Board> boards = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}
