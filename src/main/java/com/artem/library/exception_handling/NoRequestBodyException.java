package com.artem.library.exception_handling;

public class NoRequestBodyException extends  RuntimeException{

    public NoRequestBodyException(String message){
        super(message);
    }
}
