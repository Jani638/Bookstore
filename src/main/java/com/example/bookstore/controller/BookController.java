package com.example.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

	private BookRepository repository; 

    public BookController(BookRepository repository){
        this.repository = repository;
    }
	
    @GetMapping("/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model ) {
        repository.deleteById(bookId);
        return "redirect:booklist";
    }
    
    


    
    
    

}
