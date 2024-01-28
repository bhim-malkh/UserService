package com.ecom.userservice.controlleradvices;
import com.ecom.userservice.dtos.ExceptionDTO;
import com.ecom.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUserNotFoundException(UserNotFoundException ex){
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), "Check the User Id, it might not exist...");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
