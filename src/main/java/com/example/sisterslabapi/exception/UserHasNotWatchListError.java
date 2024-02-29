package com.example.sisterslabapi.exception;

public class UserHasNotWatchListError extends RuntimeException {
    public UserHasNotWatchListError(String message) {
        super(message);
    }
}