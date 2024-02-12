package com.homework.exceptions;

public class InvalidTicketException extends Exception{
    public InvalidTicketException(){
        super();
    }
    public InvalidTicketException(String msg){
        super(msg);
    }
}
