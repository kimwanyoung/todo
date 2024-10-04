package org.src.todo.dto.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class TodoRequestDto {
    private final String contents;
    private final String password;
    private final Long userId;
}
