package org.src.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity<Long> create(@RequestBody TodoRequestDto todoRequestDto) {
        return new ResponseEntity<>(this.todoService.create(todoRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public HttpEntity<PagedModel<TodoResponseDto>> readAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(new PagedModel<>(this.todoService.readAll(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    public HttpEntity<TodoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.todoService.findById(id));
    }

    @PatchMapping("/{id}")
    public HttpEntity<Long> update(@PathVariable Long id, @RequestBody TodoUpdateDto todo) {
        return ResponseEntity.ok(this.todoService.update(id, todo));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.todoService.delete(id));
    }
}
