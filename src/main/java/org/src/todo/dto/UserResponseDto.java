package org.src.todo.dto;

import lombok.Getter;
import org.src.todo.entity.User;

@Getter
public class UserResponseDto {
    private final Integer id;
    private final String userName;
    private final String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getName();
        this.email = user.getEmail();
    }
}
