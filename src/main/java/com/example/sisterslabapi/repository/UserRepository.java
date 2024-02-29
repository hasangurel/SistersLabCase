package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
