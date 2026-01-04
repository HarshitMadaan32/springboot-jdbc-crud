package com.harshit.database.dao.impl;

import com.harshit.database.TestDataUtil;
import com.harshit.database.dao.AuthorDao;
import com.harshit.database.domain.Author;
import com.harshit.database.domain.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    public AuthorDao authorDao;
    public final BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao){
        this.underTest = underTest;
        this.authorDao = authorDao;
    }
    @Test
    public void TestBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);


        Books book = TestDataUtil.CreateBookTest();
        book.setAuthor_id(author.getId());
        underTest.create(book);

        Optional<Books> result = underTest.find(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void TestMultipleBooksCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);

        Books bookA = TestDataUtil.CreateBookTest();
        bookA.setAuthor_id(authorA.getId());
        underTest.create(bookA);

        Books bookB = TestDataUtil.CreateBookTestB();
        bookB.setAuthor_id(authorA.getId());
        underTest.create(bookB);

        Books bookC = TestDataUtil.CreateBookTestC();
        bookC.setAuthor_id(authorA.getId());
        underTest.create(bookC);

        List<Books> results = underTest.findMany();

        assertThat(results).hasSize(3).containsExactly(bookA,bookB,bookC);

    }

    @Test
    public void BookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Books bookA = TestDataUtil.CreateBookTest();
        bookA.setAuthor_id(author.getId());
        underTest.create(bookA);

        bookA.setTitle("UPDATED");

       underTest.update(bookA.getIsbn(),bookA);
       Optional<Books> result = underTest.find(bookA.getIsbn());

       assertThat(result).isPresent();
       assertThat(result.get()).isEqualTo(bookA);
    }
    @Test
    public void BookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Books bookA = TestDataUtil.CreateBookTest();
        bookA.setAuthor_id(author.getId());
        underTest.create(bookA);

        underTest.delete(bookA.getIsbn());

        Optional<Books> book = underTest.find(bookA.getIsbn());
        assertThat(book).isEmpty();
    }
}
