package org.src.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

    public Page<TodoResponseDto> readAll(Pageable pageable) {
        Page<Todo> todoPages = this.todoRepository.readAll(pageable);

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
        if (todo != null) {
            if (Objects.equals(todoUpdateDto.getUser_id(), todo.getUser().getUser_id()) &&
                    Objects.equals(todoUpdateDto.getPassword(), todo.getPassword())) {
                this.todoRepository.update(id, todoUpdateDto.getContents());
                return id;
            } else {
                throw new IllegalStateException("올바르지 않은 사용자입니다.");
            }
        }
        throw new IllegalStateException("존재하지 않는 게시물 입니다.");
    }

    public Long delete(Long id) {
        Todo todoResponseDto = this.todoRepository.findById(id);
        if (todoResponseDto != null) {
            this.todoRepository.delete(id);
            return id;
        }
        throw new IllegalStateException("존재하지 않는 일정 입니다.");
    }
}
