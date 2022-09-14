package com.example.coollibrary.exception_handling.EmployeeHandler;

public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message){

        super(message);
    }
}
