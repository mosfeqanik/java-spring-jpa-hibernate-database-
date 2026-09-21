package com.techblogplatformproject.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Mark this class as a JPA Entity so it maps to a database table
@Entity
public class Author {

    // TODO: Define the Primary Key
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // RELATIONSHIP MAPPING SECTION:

    // TODO: Define the relationship: One Author has Many BlogPosts
    // Challenge 1: Use 'mappedBy' to tell Hibernate that 'BlogPost' owns the relationship.
    //              (Look at the variable name inside BlogPost.java!)
    // Challenge 2: Configure 'cascade' so that deleting an Author AUTOMATICALLY deletes all their Posts.
    //              (We want full control: SAVE, UPDATE, DELETE)
    // Challenge 3: Set 'fetch' to LAZY to improve performance.
    //              (We don't want to load 1000 posts every time we load one author)
    @OneToMany(
        mappedBy = "author",
        cascade = CascadeType.ALL
    )
    
    // TODO: Add the annotation to stop "Infinite Recursion" (Loops) during JSON conversion.
    //       (Since Post shows the Author, we must HIDE the list here)
    @JsonIgnore
    private List<BlogPost> blogPosts = new ArrayList<>();

    // Constructors
    public Author() {
        
    }

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // TODO: Write Getters and Setters for all fields:
    // 1. id
    // 2. name
    // 3. email
    // 4. blogPosts
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }
}


