package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.comment.CreateCommentRequest;
import com.example.sisterslabapi.dto.request.comment.UpdateCommentRequest;
import com.example.sisterslabapi.dto.response.comment.CreateCommentResponse;
import com.example.sisterslabapi.dto.response.comment.GetCommentResponse;
import com.example.sisterslabapi.dto.response.comment.UpdateCommentResponse;
import com.example.sisterslabapi.model.Comment;
import com.example.sisterslabapi.repository.CommentRepository;
import com.example.sisterslabapi.repository.MovieRepository;
import com.example.sisterslabapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentConverter {
    private final CommentRepository repository;
    private final MovieConverter movieConverter;
    private final UserConverter userConverter;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse convertEntityToResponse(Comment comment) {
        repository.save(comment);
        return CreateCommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .movieId(movieConverter.convertEntityToGetResponse(comment.getMovie()).id())
                .userId(userConverter.convertEntityToGetResponse(comment.getUser()).id())
                .build();
    }
    @Transactional
    public UpdateCommentResponse convertEntityToUpdateResponse(Comment comment) {
        repository.save(comment);
        return UpdateCommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .movieId(movieConverter.convertEntityToGetResponse(comment.getMovie()).id())
                .userId(userConverter.convertEntityToGetResponse(comment.getUser()).id())
                .build();
    }
    @Transactional
    public Comment convertUpdateRequestToEntity(UpdateCommentRequest request) {
        findById(request.id()).setComment(request.comment());
        return findById(request.id());
    }
    @Transactional
    public List<GetCommentResponse> convertEntityToGetAllResponse(List<Comment> comments) {

        return comments.stream().map(comment -> GetCommentResponse.builder()
                .comment(comment.getComment())
                .build()).toList();
    }
    @Transactional
    public Comment convertCreateRequestToEntity(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setComment(request.comment());
        comment.setMovie(movieRepository.findById(request.movieId())
                .orElseThrow(() -> new RuntimeException("Movie not found")));
        comment.setUser(userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        return comment;
    }
    @Transactional
    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("WatchList not found"));
    }
}
