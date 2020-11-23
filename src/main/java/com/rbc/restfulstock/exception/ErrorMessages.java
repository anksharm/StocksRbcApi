package com.rbc.restfulstock.exception;

public class ErrorMessages extends  Exception{

    public  int code;
    public String message;

    /**
     * Description: Stores the Message for all the Exceptions.
     * @param message
     */
    public ErrorMessages(String message) {
        super(message);
    }


}
