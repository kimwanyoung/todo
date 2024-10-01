package org.src.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponseDto create(@RequestBody TodoRequestDto todoRequestDto) {
        return this.todoService.create(todoRequestDto);
    }

    @GetMapping
    public List<TodoResponseDto> readAll() {
        return this.todoService.readAll();
    }

    @GetMapping("/{id}")
    public TodoResponseDto findById(@PathVariable Long id) {
        return this.todoService.findById(id);
    }


}
