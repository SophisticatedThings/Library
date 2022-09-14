package com.example.coollibrary.exception_handling;

public class NoRequestBodyException extends  RuntimeException{

    public NoRequestBodyException(String message){
        super(message);
    }
}
