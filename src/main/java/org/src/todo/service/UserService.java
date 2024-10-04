package org.src.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.src.todo.dto.user.UserRequestDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.entity.User;
import org.src.todo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User createdUser = this.userRepository.create(userRequestDto);
        return new UserResponseDto(createdUser);
    }
}
