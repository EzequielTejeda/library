package com.sondeos.library.services.impl;

import com.sondeos.library.models.Book;
import com.sondeos.library.repositories.BookRepository;
import com.sondeos.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void getByIdAndDelete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getByTitle(String titulo) {
        return bookRepository.findByTitulo(titulo);
    }
}
