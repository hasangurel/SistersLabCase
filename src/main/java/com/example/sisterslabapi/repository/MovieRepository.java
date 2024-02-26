package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
