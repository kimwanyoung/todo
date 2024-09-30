package org.src.todo.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.src.todo.entity.User;

@Getter
@RequiredArgsConstructor
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
