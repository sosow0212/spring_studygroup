package com.example.spring_studygroup.service;

import com.example.spring_studygroup.domain.team.Team;
import com.example.spring_studygroup.domain.todo.Todo;
import com.example.spring_studygroup.domain.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    // 미완료, 완료

    private final TodoRepository todoRepository;

    public List<Todo> findAllTodo(int teamId) {
        List<Todo> todos = todoRepository.findAllByTeamId(teamId);
        return todos;
    }

    public void saveTodo(String content, Team team) {
        Todo todo = new Todo();
        todo.setTeam(team);
        todo.setContent(content);
        todo.setState("미완료");
        todoRepository.save(todo);
    }

    public void doneTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId);
        todo.setState("완성");
        todoRepository.save(todo);
    }

    public void deleteTodo(int todoId) {
        todoRepository.deleteById(todoId);
    }
}
