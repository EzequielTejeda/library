package com.sondeos.library;

import com.sondeos.library.models.Book;
import com.sondeos.library.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(BookRepository bookRepository){
		return (args) -> {
			Book book1 = new Book("Rayuela", "Julio Cortazar", "28-06-1963", 100);
			Book book2 = new Book("El resplandor", "Stephen King", "28-01-1977", 200);
			Book book3 = new Book("La vuelta al mundo en ochenta días", "Julio Verne", "07-11-1872", 300);

			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);
		};
	}
}
