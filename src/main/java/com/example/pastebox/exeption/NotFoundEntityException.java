package com.example.pastebox.exeption;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String s) {
        super(s);
    }
}
