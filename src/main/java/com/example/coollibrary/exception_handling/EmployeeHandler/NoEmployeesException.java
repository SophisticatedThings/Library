package com.example.coollibrary.exception_handling.EmployeeHandler;

public class NoEmployeesException extends RuntimeException{

    public NoEmployeesException(String message ){

        super(message);
    }
}
