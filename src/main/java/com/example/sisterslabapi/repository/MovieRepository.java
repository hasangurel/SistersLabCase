package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Category;
import com.example.sisterslabapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>{
}
