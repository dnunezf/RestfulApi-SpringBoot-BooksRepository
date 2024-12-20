package com.david.database.repositories;

import com.david.database.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

// WITH THE PagingAndSortingRepository WE CAN IMPLEMENT PAGINATION, TO CONTROL THE AMOUNT OF
// BOOKS THAT ARE RETURN IN THE LIST

// A WAY TO TEST THE PAGINATION IN POSTMAN:
// USING GET: http://localhost:8080/books?size=1
//            http://localhost:8080/books?size=1&page=1

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String>, PagingAndSortingRepository<BookEntity, String>
{

}
