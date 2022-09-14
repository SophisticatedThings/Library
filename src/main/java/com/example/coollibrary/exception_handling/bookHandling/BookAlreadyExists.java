package com.example.coollibrary.exception_handling.bookHandling;

public class BookAlreadyExists extends RuntimeException {

        public BookAlreadyExists(String message){
            super(message);
        }
}
