package org.src.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.src.todo.dto.todo.TodoDeleteDto;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.dto.todo.TodoUpdateDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.entity.Todo;
import org.src.todo.entity.User;
import org.src.todo.repository.TodoRepository;
import org.src.todo.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public Long create(TodoRequestDto todoRequestDto) {
        Long userId = todoRequestDto.getUserId();
        User user = this.userRepository.findById(userId);

        if (user != null) {
            return this.todoRepository.create(todoRequestDto);
        }

        throw new IllegalStateException("해당하는 유저가 없습니다.");
    }

    public Page<TodoResponseDto> findAll(Pageable pageable) {
        Page<Todo> todoPages = this.todoRepository.findAll(pageable);

        List<TodoResponseDto> todos = todoPages.getContent().stream()
                .map(TodoResponseDto::new)
                .toList();

        return new PageImpl<>(todos, todoPages.getPageable(), todoPages.getTotalPages());
    }

    public TodoResponseDto findById(Long id) {
        Todo todo = this.todoRepository.findById(id);
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        UserResponseDto userResponseDto = new UserResponseDto(todo.getUser());
        todoResponseDto.setUser(userResponseDto);
        return todoResponseDto;
    }

    public Long update(Long id, TodoUpdateDto todoUpdateDto) {
        Todo todo = this.todoRepository.findById(id);
        if (todo != null && validateWriter(todo, todoUpdateDto.getPassword())) {
            return this.todoRepository.update(id, todoUpdateDto.getContents());
        }
        throw new IllegalStateException("올바르지 않은 사용자입니다.");
    }

    public Long delete(Long id, TodoDeleteDto todoDeleteDto) {
        Todo todo = this.todoRepository.findById(id);
        if (todo != null && validateWriter(todo, todoDeleteDto.getPassword())) {
            return this.todoRepository.delete(id, todoDeleteDto.getPassword());
        }
        throw new IllegalStateException("잘못된 입력입니다.");
    }

    private boolean validateWriter(Todo todo, String password) {
        return Objects.equals(password, todo.getPassword());
    }
}
