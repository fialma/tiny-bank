package com.teya.bank.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exceptionHandler(Exception e){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFoundExceptionHandler(UserNotFoundException e){
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage userNotActiveExceptionHandler(UserNotActiveException e){
        return new ErrorMessage(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage accountNotActiveExceptionHandler(AccountNotActiveException e){
        return new ErrorMessage(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage insufficientBalanceExceptionHandler(InsufficientBalanceException e){
        return new ErrorMessage(HttpStatus.FORBIDDEN, e.getMessage());
    }
}
