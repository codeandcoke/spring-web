package com.sanvalero.library.controller;

import com.sanvalero.library.domain.LibraryResponse;
import com.sanvalero.library.repository.BookRepository;
import com.sanvalero.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable long id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<LibraryResponse> modifyBook(@PathVariable long id, @RequestBody Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.status(404).body(new LibraryResponse("Libro no encontrado"));
        }

        bookRepository.save(book);
        return ResponseEntity.status(200).body(new LibraryResponse(""));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<LibraryResponse> deleteBook(@PathVariable long id) {
        if (bookRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(404).body(new LibraryResponse("Libro no encontrado: " + id));
        }
        bookRepository.deleteById(id);
        return ResponseEntity.status(200).body(new LibraryResponse(""));
    }
}
