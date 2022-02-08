package com.example.spring_studygroup.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
