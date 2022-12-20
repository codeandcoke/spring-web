package com.codeandcoke.library.service;

import com.codeandcoke.library.domain.Book;
import com.codeandcoke.library.exception.BookNotFoundException;
import com.codeandcoke.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(long id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book editBook(long id, Book book) {
        return null;
    }

    @Override
    public void removeBook(long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }
}
