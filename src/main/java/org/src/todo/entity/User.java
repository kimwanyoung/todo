package org.src.todo.entity;

import lombok.*;
import org.src.todo.dto.user.UserResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long user_id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
