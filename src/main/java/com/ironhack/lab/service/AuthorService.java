package com.ironhack.lab.service;

import com.ironhack.lab.exception.ResourceNotFoundException;
import com.ironhack.lab.model.Author;
import com.ironhack.lab.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Integer id, Author updatedAuthor) {
        Author author = getAuthorById(id);
        author.setName(updatedAuthor.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Integer id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
