package com.example.sisterslabapi.controller;

import com.example.sisterslabapi.request.rating.CreateRatingRequest;
import com.example.sisterslabapi.request.rating.UpdateRatingRequest;
import com.example.sisterslabapi.response.rating.CreateRatingResponse;
import com.example.sisterslabapi.response.rating.GetRatingResponse;
import com.example.sisterslabapi.response.rating.UpdateRatingResponse;
import com.example.sisterslabapi.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService service;

    @PostMapping("/create")
    public ResponseEntity<CreateRatingResponse> create(@RequestBody CreateRatingRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<GetRatingResponse> get(@PathVariable Long id){
            return new ResponseEntity<>(service.getRating(id), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<UpdateRatingResponse> update(@RequestBody UpdateRatingRequest request){
        return new ResponseEntity<>(service.update(request), HttpStatus.OK);
    }

}
