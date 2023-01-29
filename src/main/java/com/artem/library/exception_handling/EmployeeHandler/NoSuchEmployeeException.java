package com.artem.library.exception_handling.EmployeeHandler;

public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message){

        super(message);
    }
}
