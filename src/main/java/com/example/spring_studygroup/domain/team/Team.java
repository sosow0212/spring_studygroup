package com.example.spring_studygroup.domain.team;

import com.example.spring_studygroup.domain.user.User;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<>();

}
