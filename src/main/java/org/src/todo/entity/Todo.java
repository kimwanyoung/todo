package org.src.todo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class Todo {
    private Long id;
    private final String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
}
