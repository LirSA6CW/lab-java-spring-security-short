package com.ironhack.lab;

import com.ironhack.lab.model.Author;
import com.ironhack.lab.model.Post;
import com.ironhack.lab.repository.AuthorRepository;
import com.ironhack.lab.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityShortApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityShortApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AuthorRepository authorRepository, PostRepository postRepository) {
        return args -> {
            Author aiko = authorRepository.save(new Author("Aiko Tanaka"));
            Author jonas = authorRepository.save(new Author("Jonas Schmidt"));
            authorRepository.save(new Author("Cas Van Dijk"));

            postRepository.save(new Post(
                    aiko,
                    "Boost Your Productivity with 10 Easy Tips",
                    "Productivity - we all want it but it seems hard to keep every day."));
            postRepository.save(new Post(
                    aiko,
                    "How to Focus",
                    "Do you ever sit down to work and find yourself distracted by everything around you?"));
            postRepository.save(new Post(
                    jonas,
                    "Learn to Speed Read in 30 Days",
                    "Knowledge, not ability, is the great determiner of learning speed."));
        };
    }
}
