package com.artem.library.exception_handling.bookHandling;

public class BookAlreadyInUsingException extends RuntimeException{

    public BookAlreadyInUsingException(String message)
    {
        super(message);
    }
}
