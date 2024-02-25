package com.example.sisterslabapi.repository;

import com.example.sisterslabapi.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {
}
