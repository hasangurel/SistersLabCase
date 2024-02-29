package com.example.sisterslabapi.exception;

public class CategoryIdNotFoundException extends RuntimeException {
    public CategoryIdNotFoundException(String message) {
        super(message);
    }
}