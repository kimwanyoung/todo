package org.src.todo.dto.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TodoRequestDto {
    private String contents;
    private String password;
    private Long userId;
}
