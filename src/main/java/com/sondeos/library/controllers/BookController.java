package com.sondeos.library.controllers;

import com.sondeos.library.dtos.BookDTO;
import com.sondeos.library.dtos.BookRequestDTO;
import com.sondeos.library.models.Book;
import com.sondeos.library.repositories.BookRepository;
import com.sondeos.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BookDTO> getBooks(){
        return bookService.getAll().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/books")
    public ResponseEntity<String> createBook(@RequestBody BookRequestDTO bookRequestDTO){

        if(bookRequestDTO.getTitulo().isEmpty() || bookRequestDTO.getAutor().isEmpty() || bookRequestDTO.getFechaLanzamiento().isEmpty()){
            return new ResponseEntity<>("Hay campos sin completar", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getPrecio() <= 0){
            return new ResponseEntity<>("El precio del libro debe ser mayor a cero", HttpStatus.BAD_REQUEST);
        }

        if(bookService.getByTitle(bookRequestDTO.getTitulo()) != null && Objects.equals(bookService.getByTitle(bookRequestDTO.getTitulo()).getAutor(), bookRequestDTO.getAutor())){
            return new ResponseEntity<>("El libro que intenta ingresar ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        }

        Book book = new Book(bookRequestDTO.getTitulo(), bookRequestDTO.getAutor(), bookRequestDTO.getFechaLanzamiento(), bookRequestDTO.getPrecio());

        bookService.saveBook(book);

        return new ResponseEntity<>("Libro agregado exitosamente!", HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<String> updateBook(@RequestBody BookRequestDTO bookRequestDTO){

        if(bookService.getById(bookRequestDTO.getId()).isEmpty()){
            return new ResponseEntity<>("El número de identificación de este libro no se encuentra registrado, vuelva a intentarlo", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getTitulo().isEmpty() || bookRequestDTO.getAutor().isEmpty() || bookRequestDTO.getFechaLanzamiento().isEmpty()){
            return new ResponseEntity<>("Hay campos sin completar", HttpStatus.BAD_REQUEST);
        }

        if(bookRequestDTO.getPrecio() <= 0){
            return new ResponseEntity<>("El precio del libro debe ser mayor a cero", HttpStatus.BAD_REQUEST);
        }

        if(bookService.getByTitle(bookRequestDTO.getTitulo()) != null && Objects.equals(bookService.getByTitle(bookRequestDTO.getTitulo()).getAutor(), bookRequestDTO.getAutor())){
            return new ResponseEntity<>("Las modificaciones que intenta realizar ya corresponden a un libro existente", HttpStatus.BAD_REQUEST);
        }

        bookService.getById(bookRequestDTO.getId()).get().setTitulo(bookRequestDTO.getTitulo());
        bookService.getById(bookRequestDTO.getId()).get().setAutor(bookRequestDTO.getAutor());
        bookService.getById(bookRequestDTO.getId()).get().setFechaLanzamiento(bookRequestDTO.getFechaLanzamiento());
        bookService.getById(bookRequestDTO.getId()).get().setPrecio(bookRequestDTO.getPrecio());

        bookService.saveBook(bookService.getById(bookRequestDTO.getId()).get());

        return new ResponseEntity<>("Libro actualizado", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteBook(@RequestParam Long id){

        if(bookService.getById(id).isEmpty()){
            return new ResponseEntity<>("El número de identificación de este libro no se encuentra registrado, vuelva a intentarlo", HttpStatus.ACCEPTED);
        }

        bookService.getByIdAndDelete(id);

        return new ResponseEntity<>("Libro borrado", HttpStatus.ACCEPTED);
    }
}
