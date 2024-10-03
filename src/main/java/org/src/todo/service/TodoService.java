package org.src.todo.service;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public Long create(TodoRequestDto todoRequestDto) {
        Long userId = todoRequestDto.getUserId();
        User user = userRepository.findById(userId);

        if(user != null) {
            this.todoRepository.create(todoRequestDto);
        }

        throw new IllegalStateException("해당하는 유저가 없습니다.");
    }

    public List<TodoResponseDto> readAll(int limit, int offset) {
        List<Todo> todos = this.todoRepository.readAll(limit, offset);

        return todos.stream()
                .map(todo -> {
                    TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
                    UserResponseDto userResponseDto = new UserResponseDto(todo.getUser());
                    todoResponseDto.setUser(userResponseDto);
                    return todoResponseDto;
                })
                .toList();
    }

    public TodoResponseDto findById(Long id) {
        Todo todo = this.todoRepository.findById(id);
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        UserResponseDto userResponseDto = new UserResponseDto(todo.getUser());
        todoResponseDto.setUser(userResponseDto);
        return todoResponseDto;
    }

    public Long update(Long id, TodoUpdateDto todo) {

        Todo todoResponseDto = this.todoRepository.findById(id);
        if(todoResponseDto != null) {
            if(todo.getContents() != null) {
                this.todoRepository.update(id, todo.getContents());
            } else {
                throw  new IllegalStateException("빈 내용은 입력할 수 없습니다.");
            }

            return id;
        }
        throw new IllegalStateException("존재하지 않는 게시물 입니다.");
    }

    public Long delete(Long id) {
        Todo todoResponseDto = this.todoRepository.findById(id);
        if(todoResponseDto != null) {
            this.todoRepository.delete(id);
            return id;
        }
        throw new IllegalStateException("존재하지 않는 일정 입니다.");
    }
}
