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

    @DeleteMapping("/delete/{id}")
    public void deleteBooks(@PathVariable(value = "id")  Long id){
        bookService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void updateBooks(@PathVariable Long id, @RequestBody Book book){
        bookService.update(book);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") Long id){
        return bookService.getById(id);
    }



}
