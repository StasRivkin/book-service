package telran.java47.book.dao;

import telran.java47.book.model.Publisher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PublisherRepository {
    List<String> findPublishersByAuthor(String authorName);

    Stream<Publisher> findDistinctByBooksAuthorsName(String authorName);

    Optional<Publisher> findById(String publisher);

    Publisher save(Publisher publisher);
}
