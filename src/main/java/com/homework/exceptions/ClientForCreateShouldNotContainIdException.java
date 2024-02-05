package com.homework.exceptions;

public class ClientForCreateShouldNotContainIdException extends Exception{
    public ClientForCreateShouldNotContainIdException(){
        super("ClientForCreateShouldNotContainId!!!");
    }
    public ClientForCreateShouldNotContainIdException(String msg){
        super(msg);
    }
}
