package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByUserIdAndMovieId(Long userId, Long movieId);
    List<Rating> findAllByUserIdAndMovieId(Long userId, Long movieId);
}
