package com.simplebank.simplebankapp.service.exception;

import com.simplebank.simplebankapp.presentation.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                         WebRequest webRequest){
        Map<String, String> mapErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String key = ((FieldError) objectError).getField();
            String value = objectError.getDefaultMessage();
            mapErrors.put(key, value);
        });

        ApiResponse apiResponse = new ApiResponse(mapErrors.toString(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handlerEmailAlreadyExistsException(EmailAlreadyExistsException exception,
                                                                     WebRequest webRequest) {

        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Object> handlerEmailNotFoundException(EmailNotFoundException exception,
                                                                WebRequest webRequest) {

        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handlerRoleNotFoundException(RoleNotFoundException exception,
                                                                WebRequest webRequest) {

        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handlerAccountNotFoundException(AccountNotFoundException exception,
                                                               WebRequest webRequest) {

        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


}
