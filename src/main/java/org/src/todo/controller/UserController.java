package org.src.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.src.todo.dto.user.UserRequestDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.service.UserService;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public HttpEntity<UserResponseDto> create(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(this.userService.create(userRequestDto), HttpStatus.CREATED);
    }
}
