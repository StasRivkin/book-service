package telran.java47.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java47.book.dao.AuthorRepository;
import telran.java47.book.dao.BookRepository;
import telran.java47.book.dao.PublisherRepository;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.dto.exceptions.AuthorExistsException;
import telran.java47.book.model.Author;
import telran.java47.book.model.Book;
import telran.java47.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    final PublisherRepository publisherRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
    public boolean addBook(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getIsbn())) {
            return false;
        }
        //Publisher
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
                .orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
        //Authors
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getName())
                        .orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
                .collect(Collectors.toSet());
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
        bookRepository.save(book);
        return true;
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    @Transactional
    public BookDto removeBook(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        bookRepository.delete(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    @Transactional
    public BookDto updateBookTitle(String isbn, String title) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(title);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public List<BookDto> findBooksByAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundException::new);
        return author.getBooks().stream().map(b->modelMapper.map(b, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBooksByPublisher(String publisherName) {
        Publisher publisher = publisherRepository.findById(publisherName).orElseThrow(telran.java47.book.dto.exceptions.EntityNotFoundException::new);
        return publisher.getBooks().stream()
                .map(b->modelMapper.map(b,BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> findBookAuthors(String isbn) {
        return bookRepository.findBookAuthorByIsbn(isbn)
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> findPublishersByAuthors(String author) {
        return publisherRepository.findDistinctByBooks_Authors_Name(author).map(Publisher::getPublisherName).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public AuthorDto removeAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundException::new);
        if (findBooksByAuthor(author.getName()).isEmpty()) {
            authorRepository.delete(author);
            return modelMapper.map(author, AuthorDto.class);
        } else throw new AuthorExistsException();
    }

}
