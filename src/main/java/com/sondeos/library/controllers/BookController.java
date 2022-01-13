package com.sondeos.library.controllers;

import com.sondeos.library.dtos.BookRequestDTO;
import com.sondeos.library.models.Book;
import com.sondeos.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Page<Book> getBooks(@RequestParam(required = false) String titulo, @RequestParam(required = false) String autor, @PageableDefault(size = 2, page = 0, sort = "autor") Pageable pageable){

        if((titulo == null || titulo.isEmpty()) && (autor == null || autor.isEmpty())){
            Page<Book> pagedBooks = bookService.getAll(pageable);
            return pagedBooks;
        }
        else if((autor == null || autor.isEmpty()) && !titulo.isEmpty()){
            Page<Book> pagedBooksByTitulo = bookService.getAllByTitulo(titulo, pageable);
            return pagedBooksByTitulo;
        }
        else if((titulo == null || titulo.isEmpty()) && !autor.isEmpty()){
            Page<Book> pagedBooksByAutor = bookService.getAllByAutor(autor, pageable);
            return pagedBooksByAutor;
        }

        Page<Book> pagedBooksByAutorAndTitulo = bookService.getByAutorAndTitulo(titulo, autor, pageable);
        return pagedBooksByAutorAndTitulo;
    }

    @PostMapping("/books")
    public ResponseEntity<String> createBook(@RequestBody BookRequestDTO bookRequestDTO){

        if(bookRequestDTO.getTitulo() == null || bookRequestDTO.getAutor() == null || bookRequestDTO.getFechaLanzamiento() == null){
            return new ResponseEntity<>("No se están enviando todos los campos necesarios", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getTitulo().isEmpty() || bookRequestDTO.getAutor().isEmpty() || bookRequestDTO.getFechaLanzamiento().isEmpty()){
            return new ResponseEntity<>("Hay campos sin completar", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getPrecio() <= 0){
            return new ResponseEntity<>("El precio del libro debe ser mayor a cero", HttpStatus.BAD_REQUEST);
        }

        List<Book> filtredBooksByTitulo = bookService.getAll().stream().filter(book -> Objects.equals(book.getTitulo(), bookRequestDTO.getTitulo())).collect(Collectors.toList());
        List<Book> filtredBooksByAutor = filtredBooksByTitulo.stream().filter(book -> Objects.equals(book.getAutor(), bookRequestDTO.getAutor())).collect(Collectors.toList());
        if(filtredBooksByAutor.size() > 0){
            return new ResponseEntity<>("Este libro ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        }

        Book book = new Book(bookRequestDTO.getTitulo(), bookRequestDTO.getAutor(), bookRequestDTO.getFechaLanzamiento(), bookRequestDTO.getPrecio());

        bookService.saveBook(book);

        return new ResponseEntity<>("Libro agregado exitosamente!", HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<String> updateAllBook(@RequestBody BookRequestDTO bookRequestDTO){

        if(bookRequestDTO.getId() == null){
            return new ResponseEntity<>("Elija un ID para poder modificar el libro", HttpStatus.BAD_REQUEST);
        }

        if(bookService.getById(bookRequestDTO.getId()).isEmpty()){
            return new ResponseEntity<>("El número de identificación de este libro no se encuentra registrado, vuelva a intentarlo", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getTitulo() == null || bookRequestDTO.getAutor() == null || bookRequestDTO.getFechaLanzamiento() == null){
            return new ResponseEntity<>("Hay campos inexistentes", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getTitulo().isEmpty() || bookRequestDTO.getAutor().isEmpty() || bookRequestDTO.getFechaLanzamiento().isEmpty()){
            return new ResponseEntity<>("Hay campos sin completar", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getPrecio() <= 0){
            return new ResponseEntity<>("El precio del libro debe ser mayor a cero", HttpStatus.BAD_REQUEST);
        }

        bookService.getById(bookRequestDTO.getId()).get().setTitulo(bookRequestDTO.getTitulo());
        bookService.getById(bookRequestDTO.getId()).get().setAutor(bookRequestDTO.getAutor());
        bookService.getById(bookRequestDTO.getId()).get().setFechaLanzamiento(bookRequestDTO.getFechaLanzamiento());
        bookService.getById(bookRequestDTO.getId()).get().setPrecio(bookRequestDTO.getPrecio());

        bookService.saveBook(bookService.getById(bookRequestDTO.getId()).get());

        return new ResponseEntity<>("Libro actualizado", HttpStatus.OK);
    }

    @PatchMapping("/books")
    public ResponseEntity<String> updateBookAtribute(@RequestBody BookRequestDTO bookRequestDTO){

        if(bookRequestDTO.getId() == null){
            return new ResponseEntity<>("Elija un ID para poder modificar el libro", HttpStatus.BAD_REQUEST);
        }

        if(bookService.getById(bookRequestDTO.getId()).isEmpty()){
            return new ResponseEntity<>("El número de identificación de este libro no se encuentra registrado, vuelva a intentarlo", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getTitulo() != null && !bookRequestDTO.getTitulo().isEmpty()){
            bookService.getById(bookRequestDTO.getId()).get().setTitulo(bookRequestDTO.getTitulo());
            bookService.saveBook(bookService.getById(bookRequestDTO.getId()).get());

            return new ResponseEntity<>("Título modificado con éxito", HttpStatus.OK);
        }

        if(bookRequestDTO.getAutor() != null && !bookRequestDTO.getAutor().isEmpty()){
            bookService.getById(bookRequestDTO.getId()).get().setAutor(bookRequestDTO.getAutor());
            bookService.saveBook(bookService.getById(bookRequestDTO.getId()).get());

            return new ResponseEntity<>("Autor modificado con éxito", HttpStatus.OK);
        }

        if(bookRequestDTO.getFechaLanzamiento() != null && !bookRequestDTO.getFechaLanzamiento().isEmpty()){
            bookService.getById(bookRequestDTO.getId()).get().setFechaLanzamiento(bookRequestDTO.getFechaLanzamiento());
            bookService.saveBook(bookService.getById(bookRequestDTO.getId()).get());

            return new ResponseEntity<>("Fecha de lanzamiento modificada con éxito", HttpStatus.OK);
        }

        if(bookRequestDTO.getPrecio() > 0){
            bookService.getById(bookRequestDTO.getId()).get().setPrecio(bookRequestDTO.getPrecio());
            bookService.saveBook(bookService.getById(bookRequestDTO.getId()).get());

            return new ResponseEntity<>("Precio modificado con éxito", HttpStatus.OK);
        }

        return new ResponseEntity<>("No a hecho ninguna modificación", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteBook(@RequestParam Long id){

        if(bookService.getById(id).isEmpty()){
            return new ResponseEntity<>("El número de identificación de este libro no se encuentra registrado, vuelva a intentarlo", HttpStatus.ACCEPTED);
        }

        bookService.getByIdAndDelete(id);

        return new ResponseEntity<>("Libro borrado", HttpStatus.OK);
    }
}
