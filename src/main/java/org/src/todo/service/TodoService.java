package org.src.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.repository.TodoRepository;
import org.src.todo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoResponseDto create(TodoRequestDto todoRequestDto) {
        int userId = todoRequestDto.getUserId();
        UserResponseDto user = userRepository.findById(userId);

        if(user != null) {
            TodoResponseDto todoResponseDto = this.todoRepository.create(todoRequestDto);
            todoResponseDto.setContents(todoRequestDto.getContents());
            todoResponseDto.setUser(user);
            return todoResponseDto;
        }

        throw new IllegalStateException("해당하는 유저가 없습니다.");
    }

    public List<TodoResponseDto> readAll() {
        return this.todoRepository.readAll();
    }

    public TodoResponseDto findById(Long id) {
        return this.todoRepository.findById(id);
    }
}
