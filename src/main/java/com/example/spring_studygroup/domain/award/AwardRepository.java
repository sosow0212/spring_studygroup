package com.example.spring_studygroup.domain.award;

import com.example.spring_studygroup.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {
    List<Award> findAllByTeam(Team team);
    Award findAwardById(int id);
}
