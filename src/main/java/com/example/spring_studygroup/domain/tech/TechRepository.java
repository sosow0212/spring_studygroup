package com.example.spring_studygroup.domain.tech;

import com.example.spring_studygroup.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechRepository extends JpaRepository<Tech, Integer> {

    List<Tech> findAllByTeam(Team team);

}
