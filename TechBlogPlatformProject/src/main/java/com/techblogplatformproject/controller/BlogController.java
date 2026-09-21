package com.techblogplatformproject.controller;

import com.techblogplatformproject.entity.*;
import com.techblogplatformproject.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Mark this class as a REST Controller so it can handle HTTP requests
@RestController
// TODO: Define the base URL for all endpoints in this controller ("/api")
@RequestMapping("/api")
public class BlogController {

    // Dependency: Define the BlogService as a private final field to be injected via the constructor
    private final BlogService blogService;

    // TODO: Inject the BlogService using Constructor Injection
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // ENDPOINTS:

    // 1. Create an Author
    // Challenge: Map this method to HTTP POST requests at "/authors"
    @PostMapping("/authors")
    // Challenge: Use @RequestBody to convert the JSON coming from the user into a Java 'Author' object
    public Author createAuthor(@RequestBody Author author) {
        // TODO: Call the service method to save the author and return the saved object
        return blogService.createAuthor(author);
    }

    // 2. Create a Post for a specific Author
    // Challenge: We need two pieces of data here:
    //    a. The 'authorId' from the URL path (e.g., /authors/1/posts)
    //    b. The 'post' data from the Request Body
    @PostMapping("/authors/{authorId}/posts")
    public BlogPost createPost(@PathVariable Long authorId, @RequestBody BlogPost post) {
        // TODO: Call the service method that links the post to the author
        return blogService.createPost(authorId,post);
    }

    // 3. Add a Comment to a specific Post
    // Challenge: Map this to POST requests at "/posts/{postId}/comments"
    // We need the 'postId' from the URL and the 'comment' data from the Request Body
    @PostMapping("/posts/{postId}/comments")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        // TODO: Call the service method to add the comment
        return blogService.addComment(postId,comment);
    }

    // 4. Get all Posts
    // Challenge: Map this method to HTTP GET requests at "/posts"
    @GetMapping("/posts")
    public List<BlogPost> getAllPosts() {
        // TODO: Call the service method to get all posts and return the list
        return blogService.getAllPosts();
    }

    // 5. Delete a Post
    // Challenge: Map this to HTTP DELETE requests
    // Note: The "{postId}" in the URL must match the variable name in the method arguments
    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable Long postId) {
        // TODO: Call the service to delete the post
        // (Remember: Deleting the post will auto-delete comments due to Cascading)
       blogService.deletePost(postId);

        // Return a success message
        return "Post and related comments deleted successfully";
    }
}


