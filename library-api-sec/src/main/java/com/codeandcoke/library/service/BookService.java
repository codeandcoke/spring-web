package com.codeandcoke.library.service;

import com.codeandcoke.library.domain.Book;
import com.codeandcoke.library.exception.BookNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book findById(long id) throws BookNotFoundException;
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);

    Book addBook(Book book);
    Book editBook(long id, Book book);
    void removeBook(long id) throws BookNotFoundException;
}
