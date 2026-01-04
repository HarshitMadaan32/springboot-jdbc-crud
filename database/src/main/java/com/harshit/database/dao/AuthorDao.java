package com.harshit.database.dao;

import com.harshit.database.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(Author author, Long id);

    void delete(long id);
}
