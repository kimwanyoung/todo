package org.src.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.src.todo.dto.user.UserRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long user_id;
    private String name;
    private String email;
    private String password;

    public User(UserRequestDto userRequestDto) {
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
        this.password = userRequestDto.getPassword();
    }
}
