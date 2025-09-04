package com.example.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.bookstore.model.BookRepository;



@Controller
public class BookController {

	private BookRepository repository; 

    public BookController(BookRepository repository){
        this.repository = repository;
    }
	
    @RequestMapping(value= {"/index"})
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    

}
