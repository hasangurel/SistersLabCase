package com.example.sisterslabapi.controller;

import com.example.sisterslabapi.dto.request.comment.CreateCommentRequest;
import com.example.sisterslabapi.dto.request.comment.UpdateCommentRequest;
import com.example.sisterslabapi.dto.response.comment.CreateCommentResponse;
import com.example.sisterslabapi.dto.response.comment.GetCommentResponse;
import com.example.sisterslabapi.dto.response.comment.UpdateCommentResponse;
import com.example.sisterslabapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")

public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{movieId}")
    public ResponseEntity<List<GetCommentResponse>> getCommentByMovieId(@PathVariable Long movieId) {
        return new ResponseEntity<>(commentService.getCommentByMovieId(movieId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        return new ResponseEntity<>(commentService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UpdateCommentResponse> updateComment(@RequestBody UpdateCommentRequest request) {
        return new ResponseEntity<>(commentService.updateComment(request), HttpStatus.OK);
    }
}
