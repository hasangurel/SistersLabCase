package com.example.sisterslabapi.exception;

public class UserAlreadyAddedThisMovieException extends RuntimeException {
    public UserAlreadyAddedThisMovieException(String message) {
        super(message);
    }
}
