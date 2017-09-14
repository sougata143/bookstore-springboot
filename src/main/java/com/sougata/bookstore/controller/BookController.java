package com.sougata.bookstore.controller;


import com.sougata.bookstore.domain.Book;
import com.sougata.bookstore.service.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    //private final Logger log = (Logger) LoggerFactory.getLogger(BookController.class);
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public Iterable<Book> listBooks(){

        return bookService.list();
    }

    @PostMapping("/save")
    public Book saveBooks(Book book){

        return bookService.save(book);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteBooks(Long id){
        bookService.delete(id);
    }

    @RequestMapping(value = "/update/{id}")
    public void updateBooks(long id, Book book){
        bookService.update(book);
    }

}
