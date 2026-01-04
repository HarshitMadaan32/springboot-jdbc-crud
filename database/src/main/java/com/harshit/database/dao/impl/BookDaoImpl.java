package com.harshit.database.dao.impl;

import com.harshit.database.dao.BookDao;
import com.harshit.database.domain.Books;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Books book) {
        jdbcTemplate.update("INSERT INTO books(isbn,title,author_id) VALUES (?,?,?)",
                book.getIsbn(),book.getTitle(),book.getAuthor_id());
    }

    @Override
    public Optional<Books> find(String isbn) {
        List<Books> results = jdbcTemplate.query(
                "SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1",
                new BooksRowMapper(),
                isbn
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Books> findMany() {
        return jdbcTemplate.query(
                "SELECT isbn,title,author_id FROM books",
                new BooksRowMapper()
        );
    }

    @Override
    public void update(String isbn, Books book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(),book.getTitle(),book.getAuthor_id(),isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isbn
        );
    }

    public static class BooksRowMapper implements RowMapper<Books>{

        @Override
        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Books.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .author_id((rs.getLong("author_id")))
                    .build();
        }
    }
}
