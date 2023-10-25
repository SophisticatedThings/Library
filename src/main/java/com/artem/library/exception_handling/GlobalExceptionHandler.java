package com.artem.library.exception_handling;


import com.artem.library.exception_handling.ClientHandling.NoClientsException;
import com.artem.library.exception_handling.ClientHandling.NoSuchClientException;
import com.artem.library.exception_handling.EmployeeHandler.NoEmployeesException;
import com.artem.library.exception_handling.EmployeeHandler.NoSuchEmployeeException;
import com.artem.library.exception_handling.bookHandling.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoRequestBodyException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchClientException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoClientsException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data,HttpStatus.OK);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoBooksException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchBookException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(BookAlreadyExists e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(BookAlreadyInUsingException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoEmployeesException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);

    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchEmployeeException e){

        IncorrectData data = new IncorrectData();
        data.setInformation(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
