package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.board.Board;
import com.example.spring_studygroup.domain.comment.Comment;
import com.example.spring_studygroup.domain.comment.CommentRepository;
import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;


    public void saveComment(User user, Board board, String text, int teamId) {
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setUser(user);
        comment.setText(text);
        comment.setTeamId(teamId);
        commentRepository.save(comment);
    }

    public List<Comment> findAllCommentByTeamId(int teamId) {
        List<Comment> comments = commentRepository.findAllByTeamId(teamId);
        return comments;
    }
}
