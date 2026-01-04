package com.harshit.database.dao.impl;

import com.harshit.database.TestDataUtil;
import com.harshit.database.domain.Author;
import org.checkerframework.checker.units.qual.A;
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
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void AuthorDaoImplCreateTestsWorks(){
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?,?,?)"),
                eq(1L),eq("Randhir Maan"),eq(40)
        );
    }

    @Test
    public void AuthorDaoImplReadOneTestWorks(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                        eq(1L)
        );
    }

    @Test
    public void AuthorDaoImplReadManyTestWorks(){
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void AuthorDaoImplUpdateTestWorks(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(author,1L);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L,"Randhir Maan", 40,1L
        );
    }

    @Test
    public void AuthorDaoImplDeleteSqlWorks(){
        underTest.delete(1L);

        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?",
                    1L
                );
    }
}
