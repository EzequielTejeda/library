package com.sondeos.library;

import com.sondeos.library.models.Book;
import com.sondeos.library.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void existBooks(){
        List<Book> books = bookRepository.findAll();
        assertThat(books,is(not(empty())));
    }

    @Test
    public void validateBookkAtributes(){
        List<Book> books = bookRepository.findAll();
        assertThat(books, hasItem(hasProperty("id", is(not(empty())))));
        assertThat(books, hasItem(hasProperty("titulo", is(not(empty())))));
        assertThat(books, hasItem(hasProperty("autor", is(not(empty())))));
        assertThat(books, hasItem(hasProperty("fechaLanzamiento", is(not(empty())))));
        assertThat(books, hasItem(hasProperty("precio", is(not(empty())))));
    }
}
