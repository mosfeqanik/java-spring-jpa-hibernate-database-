package com.techblogplatformproject.repository;

import com.techblogplatformproject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}


