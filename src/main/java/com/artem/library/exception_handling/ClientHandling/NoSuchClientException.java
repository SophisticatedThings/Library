package com.artem.library.exception_handling.ClientHandling;

public class NoSuchClientException extends RuntimeException{

    public NoSuchClientException(String message){
        super(message);
    }
}
