package com.artem.library.exception_handling.bookHandling;

public class BookAlreadyExists extends RuntimeException {

        public BookAlreadyExists(String message){
            super(message);
        }
}
