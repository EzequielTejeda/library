package com.sondeos.library.repositories;

import com.sondeos.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository <Book, Long> {
    List<Book> findByTitulo(String titulo);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByTitulo(String titulo, Pageable pageable);

    Page<Book> findAllByAutor(String autor, Pageable pageable);

    Page<Book> findByAutorAndTitulo(String titulo, String autor, Pageable pageable);
}
