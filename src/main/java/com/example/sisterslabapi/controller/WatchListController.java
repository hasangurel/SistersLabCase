package com.example.sisterslabapi.controller;

import com.example.sisterslabapi.dto.request.watchList.CreateWatchListRequest;
import com.example.sisterslabapi.dto.response.watchList.CreateWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.GetWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.UpdateWatchListResponse;
import com.example.sisterslabapi.service.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchlist")
public class WatchListController {
    private final WatchListService watchListService;

    @PostMapping
    public ResponseEntity<CreateWatchListResponse> create(@RequestBody CreateWatchListRequest request) {
        return new ResponseEntity<>(watchListService.createWatchList(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateWatchListResponse> setAsWatched(@PathVariable Long id) {
        return new ResponseEntity<>(watchListService.setAsWatched(id), HttpStatus.OK);
    }

    @PutMapping("/notWatched/{id}")
    public ResponseEntity<UpdateWatchListResponse> setAsNotWatched(@PathVariable Long id) {
        return new ResponseEntity<>(watchListService.setAsNotWatched(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        watchListService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byUserId/{id}")
    public ResponseEntity<List<GetWatchListResponse>> getAllByUserId(@PathVariable Long id) {

        return new ResponseEntity<>(watchListService.getByUserID(id), HttpStatus.OK);
    }

    @DeleteMapping("/byUserId/{id}")
    public ResponseEntity<Void> deleteAllByUserId(@PathVariable Long id) {
        watchListService.deleteAllByUserId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
