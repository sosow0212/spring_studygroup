package com.example.spring_studygroup.domain.team;

import com.example.spring_studygroup.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findByName(String name);
}
