package com.example.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;

@RestController
public class BookRestController {

    private BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> bookListRest(){
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> findBookRest(@PathVariable("id") Long bookId){
        return bookRepository.findById(bookId);
    }
}
