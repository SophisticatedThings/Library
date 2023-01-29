package com.artem.library.exception_handling.bookHandling;



public class NoBooksException extends RuntimeException {

    public NoBooksException(String message){

        super(message);
    }

}
