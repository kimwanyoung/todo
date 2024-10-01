package org.src.todo.dto.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TodoUpdateDto {
    private final String contents;
    private final String name;
}
