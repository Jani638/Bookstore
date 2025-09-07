package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository) {
	return (args) -> {
		log.info("save categories");
        Category programming = categoryRepository.save(new Category("Programming"));
        Category java = categoryRepository.save(new Category("Java"));
        Category software = categoryRepository.save(new Category("Software Engineering"));

		log.info("save books");
	   	repository.save(new Book("Spring Boot Basics", "John Doe", 2020, "758-2394-24", 29.90, programming));
        repository.save(new Book("Java Programming", "Jane Smith", 2013, "4738-759", 39.90, java));
        repository.save(new Book("Clean Code", "Robert C. Martin", 2008, "192-3876-749", 42.50, software));

		log.info("fetch all books");
			for (Book book : repository.findAll()) {
    		log.info(book.toString());
			}
		};
	}
}
