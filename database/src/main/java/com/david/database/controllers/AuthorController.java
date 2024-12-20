package com.david.database.controllers;

import com.david.database.domain.dto.AuthorDto;
import com.david.database.domain.entities.AuthorEntity;
import com.david.database.mappers.Mapper;
import com.david.database.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController
{
    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    // CRUD FUNCTIONALITY (USES AUTHOR_DTO, IT IS WRONG TO USE THE ENTITY HERE, BECAUSE WE DON'T WANT OUR PRESENTATION
    // LAYER TO HAVE KNOWLEDGE OF THE PERSISTANCE LAYER)
    // WRAP IN A RESPONSE ENTITY, TO CONTROL THINGS LIKE STATUS CODE

    // CREATE ENDPOINT(NO ID)
    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author)
    {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        AuthorDto savedAuthorDto = authorMapper.mapTo(savedAuthorEntity);

        return new ResponseEntity<>(savedAuthorDto, HttpStatus.CREATED);
    }

    // READY MANY ENDPOINT
    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors()
    {
        List<AuthorEntity> authors = authorService.findAll();

        // THIS WILL TO EVERYTHING TO RETURN A LIST OF AUTHORS
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

    // READ ONE ENDPOINT
    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id)
    {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(id);

        return foundAuthor.map(authorEntity ->
        {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // FULL UPDATE ENDPOINT
    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto)
    {
        // IF THE AUTHOR DOES NOT EXIST
        if (!authorService.isExists(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        AuthorDto savedAuthorDto = authorMapper.mapTo(savedAuthorEntity);

        return new ResponseEntity<>(savedAuthorDto, HttpStatus.OK);
    }

    // PARTIAL UPDATE ENDPOINT
    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto)
    {
        // IF THE AUTHOR DOES NOT EXIST
        if (!authorService.isExists(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthorEntity = authorService.partialUpdate(id, authorEntity);
        AuthorDto updatedAuthorDto = authorMapper.mapTo(updatedAuthorEntity);

        return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);
    }

    // DELETE ENDPOINT
    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id)
    {
        authorService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
