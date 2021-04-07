package com.sanvalero.library.repository;

import com.sanvalero.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
}
