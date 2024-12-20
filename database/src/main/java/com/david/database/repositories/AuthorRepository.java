package com.david.database.repositories;

import com.david.database.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>{ // ID TYPE LONG PRIMARY KEY
    // CUSTOM QUERIES

    // IT IS NOT NECESSARY TO PROVIDE AN IMPLEMENTATION, BASED ON THE METHOD NAME SPRING JPA ANALYZE THE CONTEXT
    Iterable<AuthorEntity> ageLessThan(int age);

    // THIS METHOD'S NAME SPRING-DATA-JPA CANNOT DO ANYTHING
    // GREATER THAN THE FIRST PARAMETER THAT WE PROVIDED
    @Query("SELECT a FROM AuthorEntity a WHERE a.age > ?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}