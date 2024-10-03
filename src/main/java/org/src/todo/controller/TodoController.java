package org.src.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.dto.todo.TodoUpdateDto;
import org.src.todo.entity.Todo;
import org.src.todo.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public Long create(@RequestBody TodoRequestDto todoRequestDto) {
        return this.todoService.create(todoRequestDto);
    }

    @GetMapping
    public List<TodoResponseDto> readAll(@RequestParam int limit, int offset) {
        return this.todoService.readAll(limit, offset);
    }

    @GetMapping("/{id}")
    public TodoResponseDto findById(@PathVariable Long id) {
        return this.todoService.findById(id);
    }

    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody TodoUpdateDto todo) {
        return this.todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.todoService.delete(id);
    }
}
