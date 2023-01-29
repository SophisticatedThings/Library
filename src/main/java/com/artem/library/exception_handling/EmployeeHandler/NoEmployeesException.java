package com.artem.library.exception_handling.EmployeeHandler;

public class NoEmployeesException extends RuntimeException{

    public NoEmployeesException(String message ){

        super(message);
    }
}
