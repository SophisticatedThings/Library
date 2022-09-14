package com.example.coollibrary.exception_handling.bookHandling;

public class NoSuchBookException extends RuntimeException {

        public NoSuchBookException(String message){
            super(message);
        }
}
