package com.sondeos.library.repositories;

import com.sondeos.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends JpaRepository <Book, Long> {
    Book findByTitulo(String titulo);
}
