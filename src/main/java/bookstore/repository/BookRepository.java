package bookstore.repository;

import com.sougata.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    /*@Query("UPDATE book set book.name = ?, book.writer = ?, book.publisher = ? where book.id = ?")
    Book update(Book book);*/
}
