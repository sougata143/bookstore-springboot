package bookstore.service;

import com.sougata.bookstore.domain.Book;

public interface BookService {

    Iterable<Book> list();
    Book save(Book book);
    void delete(Long id);
    void update(Book book);
    Book getById(long id);


}
