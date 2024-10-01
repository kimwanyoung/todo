package org.src.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.src.todo.dto.user.UserRequestDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.create(userRequestDto);
    }
}
