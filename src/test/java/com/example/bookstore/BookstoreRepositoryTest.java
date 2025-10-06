package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.model.AppUserRepository;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)

public class BookstoreRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired AppUserRepository appUserRepository;

    @Autowired CategoryRepository categoryRepository;

    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = bookRepository.findByAuthor("John Doe");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Spring Boot Basics");
    }

    @Test
    public void createNewBook() {
        Book book = new Book("Test Driven Development", "Kent Beck", 2003, "978-0321146533", 34.99, categoryRepository.findByName("Programming").get(0));
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByTitle("Clean Code");
        assertThat(books).hasSize(1);
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> deletedBooks = bookRepository.findByTitle("Clean Code");
        assertThat(deletedBooks).isEmpty();
    }
    @Test
    public void findByCategoryShouldReturnBooks() { 
        List<Book> books = bookRepository.findByTitle("Java Programming");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getCategory().getName()).isEqualTo("Java");
    }
    @Test
    public void userRepositoryShouldFindUserByUsername() {
        assertThat(appUserRepository.findByUsername("user")).isNotNull();
    }
    @Test
    public void categoryRepositoryShouldFindCategoryByName() {
        assertThat(categoryRepository.findByName("Software Engineering")).hasSize(1);
    }
    @Test
    public void categoryRepositoryShouldCreateNewCategory() {
        categoryRepository.save(new com.example.bookstore.model.Category("Test Category"));
        assertThat(categoryRepository.findByName("Test Category")).hasSize(1);
    }
    @Test
    public void categoryRepositoryShouldDeleteCategory() {
        categoryRepository.save(new com.example.bookstore.model.Category("Test Category"));
        List<com.example.bookstore.model.Category> categories = categoryRepository.findByName("Test Category");
        assertThat(categories).hasSize(1);
        categoryRepository.delete(categories.get(0));
        assertThat(categoryRepository.findByName("Test Category")).isEmpty();
    }

    @Test
    public void findByIsbnShouldReturnBook() {
        List<Book> books = bookRepository.findByIsbn("758-2394-24");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Spring Boot Basics");
    }

    @Test
    public void findByPublicationYearShouldReturnBooks() {
        List<Book> books = bookRepository.findByPublicationYear(2013);
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Java Programming");
    }
    @Test
    public void findByPriceShouldReturnBooks() {
        List<Book> books = bookRepository.findByPrice(42.50);
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Clean Code");
    }
    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = bookRepository.findByTitle("Spring Boot Basics");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("John Doe");
    }
}
