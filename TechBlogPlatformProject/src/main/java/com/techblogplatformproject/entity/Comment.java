package com.techblogplatformproject.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Mark this class as a JPA Entity
@Entity
public class Comment {

    // TODO: Define the Primary Key
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String commenterName;
    private String text;

    // RELATIONSHIP: BLOG POST 

    // TODO: Define the relationship: Many Comments belong to One BlogPost.
    // Challenge 1: Use 'EAGER' fetching.
    //              (Reason: A comment makes no sense without knowing which post it belongs to.)
    @ManyToOne(fetch = FetchType.EAGER)
    
    // TODO: Define the Foreign Key column name in the database with name "blogpost_id".
    @JoinColumn(name = "blogpost_id")
    
    // TODO: Add the annotation to stop the JSON "Infinite Loop".
    //       (Reason: The BlogPost already lists its comments. If we list the Post here again,
    //        Java will cycle forever: Post -> Comment -> Post -> Comment...)
    @JsonIgnore
    private BlogPost blogPost;

    // Constructors
    public Comment() {

    }

    public Comment(String commenterName, String text, BlogPost blogPost) {
        this.commenterName = commenterName;
        this.text = text;
        this.blogPost = blogPost;
    }

    // TODO: Generate Getters and Setters for:
    // 1. id
    // 2. commenterName
    // 3. text
    // 4. blogPost
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }
}


