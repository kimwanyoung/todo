package org.src.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Todo {
    private Long todo_id;
    private String contents;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
}
