package com.david.database.services;

import com.david.database.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity book);

    List<BookEntity> findAll();

    // TO IMPLEMENT PAGINATION (CONTROL AMOUNT OF RETURNS IN THE LIST OF BOOKS)
    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
