package com.techblogplatformproject.service;

import com.techblogplatformproject.entity.*;
import com.techblogplatformproject.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

// TODO: Mark this class as a Service component so Spring can manage it
@Service
public class BlogService {

    // TODO: Define the 3 Repositories as private final fields
    // (AuthorRepository, BlogPostRepository, CommentRepository)
    // Repositories
    private final AuthorRepository authorRepository;
    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;

    // TODO: Inject the repositories using Constructor Injection
   public BlogService(
            AuthorRepository authorRepository,
            BlogPostRepository blogPostRepository,
            CommentRepository commentRepository) {

        this.authorRepository = authorRepository;
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }

    // Logic Methods:

    // 1. Create Author
    // Challenge: Just save the author to the database.
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // 2. Create Post for an Author
    // Challenge: We must find the Author first to link the Post to them.
    public BlogPost createPost(Long authorId, BlogPost post) {
        // TODO: Find the Author by ID.
        Author author = authorRepository
                        .findById(authorId)
                        .orElse(null);
        // 2. Check and Throw Exception if Author not found
        if (author == null) {
            throw new RuntimeException("Author not found");
        }

        // TODO: Link the Author to the Post (Foreign Key)
        post.setAuthor(author);
        
        // TODO: Save the post and return it.
        return blogPostRepository.save(post);
    }

    // 3. Add Comment to Post
    // Challenge: Find the Post first, then link the Comment to it.
    public Comment addComment(Long postId, Comment comment) {
        // TODO: Try to find the post by ID. If not found, return null.
        BlogPost post = blogPostRepository
                .findById(postId).orElse(null);

        // Check if the post is missing.
        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        // TODO: Link the Post to the Comment, Save and return the Comment.
       // Link Post to Comment
        comment.setBlogPost(post);

        // Save Comment
        return commentRepository.save(comment);


    }

    // 4. Delete Post
    // Challenge: Delete the post by ID.
    // Note: Since we set CascadeType.REMOVE in BlogPost, this will auto-delete comments!
    public void deletePost(Long postId) {
               blogPostRepository.deleteById(postId);

    }

    // 5. Get All Posts
    // Challenge: Return a list of all posts.
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }
}


