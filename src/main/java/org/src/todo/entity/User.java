package org.src.todo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private Integer id;
    private final String name;
    private final String email;
    private final String password;
}
