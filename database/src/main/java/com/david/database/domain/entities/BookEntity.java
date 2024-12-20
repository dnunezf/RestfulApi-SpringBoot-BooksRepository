package com.david.database.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// HAVING TROUBLES WITH LOMBOK
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity
{
    @Id
    private String isbn;

    private String title;

    // FOREIGN KEY
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;

    public BookEntity(String isbn, String title, AuthorEntity authorEntity) {
        this.isbn = isbn;
        this.title = title;
        this.authorEntity = authorEntity;
    }

    public BookEntity() {
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

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }
}
