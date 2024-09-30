package org.src.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.service.TodoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponseDto create(@RequestBody TodoRequestDto todoRequestDto) {
        System.out.println(todoRequestDto);
        return this.todoService.create(todoRequestDto);
    }
}
