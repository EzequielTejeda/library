package com.sondeos.library.services;

import com.sondeos.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll();

    Optional<Book> getById(Long id);

    Book saveBook(Book book);

    void getByIdAndDelete(Long id);

    List<Book> getByTitle(String titulo);

    Page<Book> getAll(Pageable pageable);

    Page<Book> getAllByTitulo(String titulo, Pageable pageable);

    Page<Book> getAllByAutor(String autor, Pageable pageable);

    Page<Book> getByAutorAndTitulo(String titulo, String autor, Pageable pageable);
}
