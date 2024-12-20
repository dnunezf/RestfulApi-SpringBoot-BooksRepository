package com.david.database.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// HAVING TROUBLES WITH LOMBOK
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class BookDto {
    private String isbn;

    private String title;

    private AuthorDto author; // USING DTO CLASS AUTHOR

    public BookDto(String isbn, String title, AuthorDto author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public BookDto() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
