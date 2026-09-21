package com.techblogplatformproject.repository;

import com.techblogplatformproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}


