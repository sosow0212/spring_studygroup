package com.example.spring_studygroup.domain.comment;

import com.example.spring_studygroup.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByTeamId(int teamId);
}
