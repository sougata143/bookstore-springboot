package com.sougata.bookstore.service;

import com.sougata.bookstore.domain.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookService {

    Iterable<Book> list();
    Book save(Book book);
    void delete(Long id);
    void update(Book book);


}
