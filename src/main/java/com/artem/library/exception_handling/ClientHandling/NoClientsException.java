package com.artem.library.exception_handling.ClientHandling;

public class NoClientsException extends RuntimeException{

    public NoClientsException(String message){

        super(message);
    }
}
