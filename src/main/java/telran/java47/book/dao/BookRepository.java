package telran.java47.book.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Author;
import telran.java47.book.model.Book;

import java.util.stream.Stream;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
    @Query("SELECT b.authors FROM Book b WHERE b.isbn = :isbn")
    Stream<Author> findBookAuthorByIsbn(String isbn);
}
