package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
