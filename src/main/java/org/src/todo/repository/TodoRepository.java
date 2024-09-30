package org.src.todo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.entity.Todo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TodoResponseDto create(TodoRequestDto todoRequestDto) {
        String sql = "INSERT INTO TODO (contents, created_at, updated_at, user_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todoRequestDto.getContents());
            ps.setTimestamp(2, Timestamp.valueOf(createdAt));
            ps.setTimestamp(3, Timestamp.valueOf(updatedAt));
            ps.setInt(4, todoRequestDto.getUserId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        return new TodoResponseDto(id, createdAt, updatedAt);
    }
}
