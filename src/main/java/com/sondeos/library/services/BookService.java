package com.sondeos.library.services;

import com.sondeos.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll();

    Optional<Book> getById(Long id);

    Book saveBook(Book book);

    void getByIdAndDelete(Long id);

    Book getByTitle(String titulo);
}
