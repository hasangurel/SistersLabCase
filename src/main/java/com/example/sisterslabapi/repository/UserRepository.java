package com.example.sisterslabapi.repository;
import com.example.sisterslabapi.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByUsername(String username);
}
