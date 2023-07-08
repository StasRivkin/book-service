package telran.java47.book.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.model.Publisher;
import telran.java47.book.service.BookService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BookController {
    final BookService bookService;

    @PostMapping("/book")
    public boolean addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping("/book/{isbn}")
    public BookDto findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @DeleteMapping("/book/{isbn}")
    public BookDto removeBook(@PathVariable String isbn) {
        return bookService.removeBook(isbn);
    }

    @PutMapping("/book/{isbn}/title/{title}")
    public BookDto updateBookTitle(@PathVariable String isbn, @PathVariable String title) {
        return bookService.updateBookTitle(isbn, title);
    }

    @GetMapping("/books/author/{author}")
    public List<BookDto> findBookByAuthor(@PathVariable String author) {
        return bookService.findBooksByAuthor(author);
    }

    @GetMapping("/books/publisher/{publisher}")
    public List<BookDto> findBookByPublisher(@PathVariable String publisher) {
        return bookService.findBooksByPublisher(publisher);
    }

    @GetMapping("/authors/book/{isbn}")
    public List<AuthorDto> findBookAuthors(@PathVariable String isbn) {
        return bookService.findBookAuthors(isbn);
    }

    @GetMapping("/publishers/author/{author}")
    public Set<String> findPublishersByAuthors(@PathVariable String author) {
        return bookService.findPublishersByAuthors(author);
    }

    @DeleteMapping("/author/{author}")
    public AuthorDto removeAuthor(@PathVariable String author) {
        return bookService.removeAuthor(author);
    }
}
