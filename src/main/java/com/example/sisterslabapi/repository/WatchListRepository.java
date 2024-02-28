package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {
    void deleteAllByUser(User user);
}
