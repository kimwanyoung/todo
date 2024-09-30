package org.src.todo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Todo {
    private Long id;
    private final String contents;
    private Long user_id;
}
