package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.response.comment.GetCommentResponse;
import com.example.sisterslabapi.model.Comment;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.repository.CommentRepository;
import com.example.sisterslabapi.dto.request.comment.CreateCommentRequest;
import com.example.sisterslabapi.dto.request.comment.UpdateCommentRequest;
import com.example.sisterslabapi.dto.response.comment.CreateCommentResponse;
import com.example.sisterslabapi.dto.response.comment.UpdateCommentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<GetCommentResponse> getCommentByMovieId(Long movieId) {
        Movie byId = movieService.findById(movieId);
        List<Comment> comments = byId.getComments();
        List<GetCommentResponse> responses = comments.stream().map(comment -> GetCommentResponse.builder()
                .comment(comment.getComment())
                .build()).toList();

        return responses;
    }
    public CreateCommentResponse create(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setUser(userService.findById(request.userId()));
        comment.setMovie(movieService.findById(request.movieId()));
        comment.setComment(request.comment());
        repository.save(comment);
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
