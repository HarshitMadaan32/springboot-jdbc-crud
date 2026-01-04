package com.harshit.database.dao;

import com.harshit.database.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Books book);

    Optional<Books> find(String isbn);

    List<Books> findMany();

    void update(String isbn, Books book);

    void delete(String isbn);
}
