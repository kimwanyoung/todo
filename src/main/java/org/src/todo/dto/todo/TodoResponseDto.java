package org.src.todo.dto.todo;

import lombok.*;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.entity.Todo;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponseDto user;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getTodo_id();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
        this.user = new UserResponseDto(todo.getUser());
    }
}
