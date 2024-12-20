package com.david.database.services.impl;

import com.david.database.domain.entities.AuthorEntity;
import com.david.database.repositories.AuthorRepository;
import com.david.database.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // CREATE (NO ID)
    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    // READ MANY
    @Override
    public List<AuthorEntity> findAll() {
        // THIS LOGIC CONVERT THIS INTO A LIST, AND CAPSULATING IT IN OUR SERVICE
        // LAYER.
        return StreamSupport.stream(authorRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    // READ ONE
    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    // PARTIAL UPDATE
    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        // THIS LOGIC WHAT IS DOING IS SAYING THAT IF WE FIND THAT THE AUTHOR ENTITY
        // PROVIDED HAS A NAME AND AGE AND IT IS NOT NULL, THEN WE WANT TO SET THAT
        // ON THE EXISTING AUTHOR FOUND IN THE DATABASE
        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author not found!"));
    }

    // DELETE
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
