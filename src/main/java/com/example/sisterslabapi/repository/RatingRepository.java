package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByUserIdAndMovieId(Long userId, Long movieId);
}
