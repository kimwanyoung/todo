package org.src.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.src.todo.dto.user.UserResponseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponseDto userResponseDto;

    public TodoResponseDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
