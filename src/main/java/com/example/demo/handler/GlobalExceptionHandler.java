package com.example.demo.handler;

import com.example.demo.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //전역에서 발생하는 예외를 처리해줌
@RestController
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e){

        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
