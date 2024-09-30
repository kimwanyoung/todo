package org.src.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.src.todo.dto.UserRequestDto;
import org.src.todo.dto.UserResponseDto;
import org.src.todo.entity.User;
import org.src.todo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), userRequestDto.getPassword());
        return this.userRepository.create(user);
    }
}
