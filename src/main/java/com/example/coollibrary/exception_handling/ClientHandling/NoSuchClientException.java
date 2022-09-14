package com.example.coollibrary.exception_handling.ClientHandling;

public class NoSuchClientException extends RuntimeException{

    public NoSuchClientException(String message){
        super(message);
    }
}
