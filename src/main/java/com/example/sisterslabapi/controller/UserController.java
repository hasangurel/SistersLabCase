package com.example.sisterslabapi.controller;

import com.example.sisterslabapi.dto.request.user.CreateUserRequest;
import com.example.sisterslabapi.dto.request.user.UpdateUserRequest;
import com.example.sisterslabapi.dto.response.user.CreateUserResponse;
import com.example.sisterslabapi.dto.response.user.GetUserResponse;
import com.example.sisterslabapi.dto.response.user.UpdateUserResponse;
import com.example.sisterslabapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(service.createUser(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<UpdateUserResponse> update(@RequestBody UpdateUserRequest request) {
        return new ResponseEntity<>(service.updateUser(request), HttpStatus.OK);
    }

    @PutMapping("/Password")
    public ResponseEntity<UpdateUserResponse> updatePassword(@RequestBody UpdateUserRequest request) {
        return new ResponseEntity<>(service.updateUserPassword(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<GetUserResponse>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
