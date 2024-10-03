package org.src.todo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.dto.todo.TodoResponseDto;
import org.src.todo.dto.user.UserResponseDto;
import org.src.todo.entity.Todo;
import org.src.todo.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long create(TodoRequestDto todoRequestDto) {
        String sql = "INSERT INTO TODO (contents, created_at, updated_at, user_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todoRequestDto.getContents());
            ps.setTimestamp(2, Timestamp.valueOf(now));
            ps.setTimestamp(3, Timestamp.valueOf(now));
            ps.setLong(4, todoRequestDto.getUserId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        return id;
    }

    public List<Todo> readAll(int limit, int offset) {
        String sql = "SELECT * FROM TODO join user on todo.user_id = user.user_id limit ? offset ?";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> todoMapper(resultSet), limit, offset);
    }

    public Todo findById(Long id) {
        String sql = "select * from todo join user on todo.user_id = user.user_id where todo_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> todoMapper(resultSet), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public void update(Long id, String contents) {
        String sql = "UPDATE TODO SET contents = ? WHERE todo_id = ?";
        jdbcTemplate.update(sql, contents, id);
    }

    public void delete(Long id) {
        String sql = "delete from todo where todo_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Todo todoMapper(ResultSet resultSet) throws SQLException {
        Todo todo = new Todo();
        todo.setTodo_id(resultSet.getLong("todo_id"));
        todo.setContents(resultSet.getString("contents"));
        todo.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        todo.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

        User user = new User();
        user.setUser_id(resultSet.getLong("user.user_id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));

        todo.setUser(user);
        return todo;
    }
}
