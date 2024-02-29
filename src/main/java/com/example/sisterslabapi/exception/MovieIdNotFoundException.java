package com.example.sisterslabapi.exception;

public class MovieIdNotFoundException extends RuntimeException {
    public MovieIdNotFoundException(String message) {
        super(message);
    }
}