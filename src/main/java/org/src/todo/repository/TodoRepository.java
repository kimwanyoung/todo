package org.src.todo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.src.todo.dto.todo.TodoRequestDto;
import org.src.todo.entity.Todo;
import org.src.todo.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long create(TodoRequestDto todoRequestDto) {
        String sql = "INSERT INTO TODO (contents, password, created_at, updated_at, user_id) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todoRequestDto.getContents());
            ps.setString(2, todoRequestDto.getPassword());
            ps.setTimestamp(3, Timestamp.valueOf(now));
            ps.setTimestamp(4, Timestamp.valueOf(now));
            ps.setLong(5, todoRequestDto.getUserId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        return id;
    }

    public Page<Todo> findAll(Pageable pageable) {
        try {
            String sql = "SELECT * FROM TODO join user on todo.user_id = user.user_id limit ? offset ?";
            String totalCountSql = "SELECT COUNT(*) FROM TODO";

            List<Todo> todos = jdbcTemplate.query(sql, todoMapper(), pageable.getPageSize(), pageable.getOffset());
            int total = jdbcTemplate.queryForObject(totalCountSql, Integer.class);

            return new PageImpl<>(todos, pageable, total);
        } catch (SQLException e) {
            return null;
        }
    }

    public Todo findById(Long id) {
        String sql = "select * from todo join user on todo.user_id = user.user_id where todo_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, todoMapper(), id);
        } catch (SQLException e) {
            return null;
        }
    }

    public Long update(Long id, String contents) {
        String sql = "UPDATE TODO SET contents = ? WHERE todo_id = ?";
        jdbcTemplate.update(sql, contents, id);
        return id;
    }

    public Long delete(Long id, String password) {
        String sql = "delete from todo where todo_id = ? and password = ?";
        jdbcTemplate.update(sql, id, password);
        return id;
    }

    private RowMapper<Todo> todoMapper() throws SQLException {
        return (resultSet, rowNum) -> {
            Todo todo = new Todo();
            todo.setTodo_id(resultSet.getLong("todo_id"));
            todo.setContents(resultSet.getString("contents"));
            todo.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            todo.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
            todo.setPassword(resultSet.getString("password"));

            User user = new User();
            user.setUser_id(resultSet.getLong("user.user_id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));

            todo.setUser(user);
            return todo;
        };
    }
}
