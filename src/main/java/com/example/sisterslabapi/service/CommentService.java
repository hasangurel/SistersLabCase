package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.Comment;
import com.example.sisterslabapi.repository.CommentRepository;
import com.example.sisterslabapi.request.comment.CreateCommentRequest;
import com.example.sisterslabapi.request.comment.UpdateCommentRequest;
import com.example.sisterslabapi.response.comment.CreateCommentResponse;
import com.example.sisterslabapi.response.comment.UpdateCommentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final UserService userService;
    private final MovieService movieService;

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CreateCommentResponse create(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setUser(userService.findById(request.userId()));
        comment.setMovie(movieService.findById(request.movieId()));
        comment.setComment(request.comment());
        return CreateCommentResponse.builder()
                .id(repository.save(comment).getId())
                .userId(request.userId())
                .movieId(request.movieId())
                .comment(request.comment())
                .build();
    }

    public UpdateCommentResponse updateComment(UpdateCommentRequest request) {
        Comment comment = findById(request.id());
        comment.setComment(request.comment());
        repository.save(comment);
        return UpdateCommentResponse.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .movieId(comment.getMovie().getId())
                .comment(comment.getComment())
                .build();
    }

    public Comment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }

}
