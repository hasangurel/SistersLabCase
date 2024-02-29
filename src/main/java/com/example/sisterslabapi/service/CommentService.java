package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.converter.CommentConverter;
import com.example.sisterslabapi.dto.request.comment.CreateCommentRequest;
import com.example.sisterslabapi.dto.request.comment.UpdateCommentRequest;
import com.example.sisterslabapi.dto.response.comment.CreateCommentResponse;
import com.example.sisterslabapi.dto.response.comment.GetCommentResponse;
import com.example.sisterslabapi.dto.response.comment.UpdateCommentResponse;
import com.example.sisterslabapi.exception.CommentNotFoundException;
import com.example.sisterslabapi.exception.Constant;
import com.example.sisterslabapi.exception.MovieIdNotFoundException;
import com.example.sisterslabapi.model.Comment;
import com.example.sisterslabapi.model.Movie;
import com.example.sisterslabapi.repository.CommentRepository;
import com.example.sisterslabapi.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final MovieRepository movieRepository;
    private final CommentConverter converter;

    @Transactional
    public void deleteById(Long id) {
        repository.delete(findById(id));
    }

    public List<GetCommentResponse> getCommentByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieIdNotFoundException(Constant.MOVIE_ID_NOT_FOUND));
        return converter.convertEntityToGetAllResponse(movie.getComments());
    }

    public CreateCommentResponse create(CreateCommentRequest request) {
        return converter.convertEntityToResponse(converter.convertCreateRequestToEntity(request));
    }

    public UpdateCommentResponse updateComment(UpdateCommentRequest request) {
        return converter.convertEntityToUpdateResponse(converter.convertUpdateRequestToEntity(request));
    }

    public Comment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CommentNotFoundException(Constant.COMMENT_NOT_FOUND));
    }
}
