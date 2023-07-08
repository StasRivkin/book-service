package telran.java47.book.service;

import org.springframework.web.bind.annotation.PathVariable;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.model.Publisher;

import java.util.List;
import java.util.Set;

public interface BookService {
    boolean addBook(BookDto bookDto);

    BookDto findBookByIsbn(String isbn);

    public BookDto removeBook(String isbn);

    public BookDto updateBookTitle(String isbn, String title);

    public List<BookDto> findBooksByAuthor(String author);

    public List<BookDto> findBooksByPublisher(@PathVariable String publisher);

    public List<AuthorDto> findBookAuthors(@PathVariable String isbn);

    public Set<String> findPublishersByAuthors(@PathVariable String author);

    public AuthorDto removeAuthor(@PathVariable String author);

}
