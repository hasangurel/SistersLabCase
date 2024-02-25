package com.example.sisterslabapi.controller;

import com.example.sisterslabapi.request.watchList.CreateWatchListRequest;
import com.example.sisterslabapi.response.watchList.CreateWatchListResponse;
import com.example.sisterslabapi.service.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchlist")
public class WatchListController {
    private final WatchListService watchListService;

    @PostMapping("/create")
    public ResponseEntity<CreateWatchListResponse> create(@RequestBody CreateWatchListRequest request) {
        return new ResponseEntity<>(watchListService.createWatchList(request), HttpStatus.CREATED);
    }
}
