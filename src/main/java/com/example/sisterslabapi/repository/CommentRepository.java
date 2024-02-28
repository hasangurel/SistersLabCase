package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentByMovie_Id(Long movieId);
}
