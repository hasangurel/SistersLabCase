package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
