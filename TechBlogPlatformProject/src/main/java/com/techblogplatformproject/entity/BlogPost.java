package com.techblogplatformproject.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

// TODO: Mark this class as a JPA Entity
@Entity
public class BlogPost {

    // TODO: Define the Primary Key
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // TODO: Add the annotation to increase the storage size.
    // Challenge: Set the length to 5000 characters to allow for long articles.
    @Column(length = 5000)
    private String content;

    // RELATIONSHIP 1: AUTHOR
    
    // TODO: Define the relationship: Many BlogPosts belong to One Author.
    // Challenge 1: Use 'EAGER' fetching.
    //              (Reason: When we load a post, we ALWAYS want to display the author's name immediately.)
    @ManyToOne(fetch = FetchType.EAGER)
    
    // TODO: Define the Foreign Key column name in the database table with name "author_id".
    @JoinColumn(name = "author_id")
    private Author author;

    // RELATIONSHIP 2: COMMENTS

    // TODO: Define the relationship: One BlogPost has Many Comments.
    // Challenge 1: Use 'mappedBy' to point to the field in the Comment class.
    // Challenge 2: Configure 'cascade' to REMOVE.
    //              (Reason: If we delete this post, the database must delete all its comments too.)
    // Challenge 3: Use 'LAZY' fetching for performance.
     @OneToMany(
        mappedBy = "blogPost",
        cascade = CascadeType.REMOVE,
        fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();

    // Constructors
    public BlogPost() {
        
    }

    public BlogPost(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // TODO: Write Getters and Setters for:
    // 1. id
    // 2. title
    // 3. content
    // 4. author
    // 5. comments
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}


