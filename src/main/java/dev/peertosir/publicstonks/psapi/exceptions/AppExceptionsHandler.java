package dev.peertosir.publicstonks.psapi.exceptions;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;
import dev.peertosir.publicstonks.psapi.model.response.errors.ErrorRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {


    //API Custom exceptions
    @ExceptionHandler(value = {PublicStonksException.class})
    public ResponseEntity<Object> handleUserServiceException(PublicStonksException ex, WebRequest request) {
        ErrorRepresentation errorRepresentation = new ErrorRepresentation(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorRepresentation, new HttpHeaders(), ex.getStatusCode());
    }

    //API all others exceptions
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
