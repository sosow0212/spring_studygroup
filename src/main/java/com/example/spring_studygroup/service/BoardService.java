package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.board.Board;
import com.example.spring_studygroup.domain.board.BoardRepository;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> findAllBoardByTeam(Team team) {
        return boardRepository.findAllByTeam(team);
    }

    public void writeBoard(Board board, Team team, User user) {
        board.setTeam(team);
        boardRepository.save(board);
    }
}
