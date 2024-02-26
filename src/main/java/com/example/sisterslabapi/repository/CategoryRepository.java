package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
