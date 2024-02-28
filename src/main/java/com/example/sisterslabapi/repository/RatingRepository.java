package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByUserIdAndMovieId(Long userId, Long movieId);
}
