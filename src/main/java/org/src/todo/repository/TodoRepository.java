package org.src.todo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.dto.user.UserResponseDto;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TodoResponseDto create(TodoRequestDto todoRequestDto) {
        String sql = "INSERT INTO TODO (contents, created_at, updated_at, user_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todoRequestDto.getContents());
            ps.setTimestamp(2, Timestamp.valueOf(now));
            ps.setTimestamp(3, Timestamp.valueOf(now));
            ps.setInt(4, todoRequestDto.getUserId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        return new TodoResponseDto(id, now, now);
    }

    public List<TodoResponseDto> readAll(int limit, int offset) {
        String sql = "SELECT * FROM TODO join user on todo.user_id = user.user_id limit ? offset ?";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            TodoResponseDto todo = new TodoResponseDto();
            todo.setId(resultSet.getLong("todo_id"));
            todo.setContents(resultSet.getString("contents"));
            todo.setCreatedAt(resultSet.getDate("created_at").toLocalDate().atStartOfDay());
            todo.setUpdatedAt(resultSet.getDate("updated_at").toLocalDate().atStartOfDay());

            UserResponseDto user = new UserResponseDto();
            user.setId(resultSet.getInt("user.user_id"));
            user.setUserName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            todo.setUser(user);
            return todo;
        }, limit, offset);
    }

    public TodoResponseDto findById(Long id) {
        String sql = "select * from todo join user on todo.user_id = user.user_id where todo_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                TodoResponseDto todo = new TodoResponseDto();
                todo.setId(id);
                todo.setContents(resultSet.getString("contents"));
                todo.setCreatedAt(resultSet.getDate("created_at").toLocalDate().atStartOfDay());
                todo.setUpdatedAt(resultSet.getDate("updated_at").toLocalDate().atStartOfDay());

                UserResponseDto user = new UserResponseDto();
                user.setId(resultSet.getInt("user.user_id"));
                user.setUserName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                todo.setUser(user);

                return todo;
            } else {
                return null;
            }
        }, id);
    }

    public void update(Long id, String contents) {
        String sql = "UPDATE TODO SET contents = ? WHERE todo_id = ?";
        jdbcTemplate.update(sql, contents, id);
    }

    public void delete(Long id) {
        String sql = "delete from todo where todo_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
