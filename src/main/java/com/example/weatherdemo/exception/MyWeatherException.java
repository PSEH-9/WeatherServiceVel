package com.example.weatherdemo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyWeatherException extends Exception {
    public MyWeatherException(String message,Throwable t)
    {
        super(message,t);
    }
}