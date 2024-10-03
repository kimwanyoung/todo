package org.src.todo.dto.user;

import lombok.*;
import org.src.todo.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String userName;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getUser_id();
        this.userName = user.getName();
        this.email = user.getEmail();
    }
}
