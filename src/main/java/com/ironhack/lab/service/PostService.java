package com.ironhack.lab.service;

import com.ironhack.lab.dto.PostRequest;
import com.ironhack.lab.exception.ResourceNotFoundException;
import com.ironhack.lab.model.Author;
import com.ironhack.lab.model.Post;
import com.ironhack.lab.repository.AuthorRepository;
import com.ironhack.lab.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    public Post createPost(PostRequest postRequest) {
        Author author = getAuthor(postRequest.getAuthorId());
        return postRepository.save(new Post(author, postRequest.getTitle(), postRequest.getPost()));
    }

    public Post updatePost(Integer id, PostRequest postRequest) {
        Post post = getPostById(id);
        Author author = getAuthor(postRequest.getAuthorId());
        post.setAuthor(author);
        post.setTitle(postRequest.getTitle());
        post.setPost(postRequest.getPost());
        return postRepository.save(post);
    }

    public void deletePost(Integer id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

    private Author getAuthor(Integer authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
    }
}
