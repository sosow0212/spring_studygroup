package com.example.spring_studygroup.domain.board;

import com.example.spring_studygroup.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByTeam(Team team);
    Board findById(int id);
}
