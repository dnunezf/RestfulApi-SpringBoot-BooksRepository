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
@Table(name = "authors")
public class AuthorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq") // GENERATES A SEQUENCE ID
    private Long id;

    private String name;

    private Integer age;

    public AuthorEntity(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public AuthorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
