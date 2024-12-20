package com.david.database.services.impl;

import com.david.database.domain.entities.BookEntity;
import com.david.database.repositories.BookRepository;
import com.david.database.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE (WITH ID)
    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity book) {
        book.setIsbn(isbn); // BE SURE THAT THE BOOK THAT WE CREATE WILL ALWAYS HAVE THE ISBN PROVIDED IN THE URL
        return bookRepository.save(book);
    }

    // READ MANY
    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    // PAGINATION
    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // READ ONE
    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    // PARTIAL UPDATE
    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
