package com.homework.exceptions;

public class IllegalfieldException extends Exception{
    public  IllegalfieldException(){
        super("ILLEGAL FIELD !!!");
    }
    public IllegalfieldException(String msg){
        super(msg);
    }
}
