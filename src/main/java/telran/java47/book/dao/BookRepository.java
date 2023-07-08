package telran.java47.book.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Author;
import telran.java47.book.model.Book;

import java.util.Set;
import java.util.stream.Stream;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName")
    Stream<Book> findByAuthorName(String authorName);

    @Query("SELECT b FROM Book b JOIN b.publisher p WHERE p.publisherName = :publisherName")
    Stream<Book> findBPublisherName(String publisherName);

    @Query("SELECT b.authors FROM Book b WHERE b.isbn = :isbn")
    Stream<Author> findBookAuthorByIsbn(String isbn);

    @Query("SELECT DISTINCT b.publisher.publisherName FROM Book b JOIN b.authors a WHERE a.name = :authorName")
    Set<String> findPublishersByAuthorName(String authorName);
}
