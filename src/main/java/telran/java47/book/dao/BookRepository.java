package telran.java47.book.dao;

import telran.java47.book.model.Book;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository {
    Stream<Book> findByAuthorsName(String name);

    Stream<Book> findByPublisherName(String name);

    boolean existsById(String isbn);

    Book save(Book book);

    Optional<Book> findById(String isbn);

    void deleteById(String isbn);
}
