package org.src.todo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.src.todo.dto.user.UserRequestDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.entity.User;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public User create(UserRequestDto userRequestDto) {
        String sql = "INSERT INTO USER(name, email, password) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userRequestDto.getName());
            preparedStatement.setString(2, userRequestDto.getEmail());
            preparedStatement.setString(3, userRequestDto.getPassword());
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        User createdUser = new User(userRequestDto);
        createdUser.setUser_id(id);
        return createdUser;
    }

    public User findById(Long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            } else {
                return null;
            }
        }, userId);
    }
}
