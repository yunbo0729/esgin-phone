package com.example.demo.common;

public class DataValidationException extends RuntimeException{
    private static final long seriaBersionUID = 6982959920994513240L;
    public DataValidationException(){

    }
    public DataValidationException(String msg){
        super(msg);
    }
}
