package telran.java47.book.dao;


import telran.java47.book.model.Author;

import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(String authorName);

    Author save(Author author);

    void delete(Author author);
}
