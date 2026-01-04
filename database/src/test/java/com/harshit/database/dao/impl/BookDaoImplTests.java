package com.harshit.database.dao.impl;

import com.harshit.database.TestDataUtil;
import com.harshit.database.domain.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void BookDaoImplTestWorks(){
        Books book = TestDataUtil.CreateBookTest();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books(isbn,title,author_id) VALUES (?,?,?)"),
                eq("101"),eq("Olive"),eq(1L)
        );
    }

    @Test
    public void BookDaoImplReadOneTestWorks(){
        underTest.find("101");

        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BooksRowMapper>any(),
                eq("101")
        );
    }


    @Test
    public void BooksDaoImplReadManyTest(){
        underTest.findMany();

        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BooksRowMapper>any()
        );
    }

    @Test
    public void BooksDaoImplUpdateSqlWorks(){
        Books book = TestDataUtil.CreateBookTest();
        underTest.create(book);
        underTest.update("101",book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "101", "Olive", 1L, "101"
        );
    }

    @Test
    public void BookDaoImplDeleteSqlWorks(){
        underTest.delete("101");
    }
}
