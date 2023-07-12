package telran.java47.book.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Publisher;

import java.util.stream.Stream;

public interface PublisherRepository extends PagingAndSortingRepository<Publisher, String> {
    Stream<Publisher> findDistinctByBooks_Authors_Name(String authorName);
}
