package com.david.database.controllers;

import com.david.database.domain.dto.BookDto;
import com.david.database.domain.entities.BookEntity;
import com.david.database.mappers.Mapper;
import com.david.database.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController
{
    private BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    // CREATE ENDPOINT (WITH ID)
    // THIS ENDPOINT ALSO WORKS AS A FULL UPDATE ENDPOINT
    // THE DIFFERENCE IS THE HTTP 201 (CREATED) AND HTTP
    // 200 (UPDATED)
    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto)
    {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);

        if (bookService.isExists(isbn))
        {
            // IF BOOK EXIST, SO UPDATE
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        }
        else
        {
            // IF BOOK DOES NOT EXIST, SO CREATE
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    // READ MANY ENDPOINT
    // HERE WE ARE IMPLEMENTING THE PAGINATION, TO CONTROL THE AMOUNT OF RETURNS IN THE
    // BOOK OF LISTS
    @GetMapping(path = "/books")
    public Page<BookDto> listBooks(Pageable pageable)
    {
        Page<BookEntity> books = bookService.findAll(pageable);

        return books.map(bookMapper::mapTo);
    }

    // READ ONE ENDPOINT
    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn)
    {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);

        return foundBook.map(bookEntity ->
        {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PARTIAL UPDATE ENDPOINT
    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto)
    {
        if (!bookService.isExists(isbn))
        {
            // IF BOOK EXIST, SO UPDATE
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
        BookDto updatedBookDto = bookMapper.mapTo(updatedBookEntity);

        return new ResponseEntity<>(updatedBookDto, HttpStatus.OK);
    }

    // DELETE ENDPOINT
    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn)
    {
        bookService.delete(isbn);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
