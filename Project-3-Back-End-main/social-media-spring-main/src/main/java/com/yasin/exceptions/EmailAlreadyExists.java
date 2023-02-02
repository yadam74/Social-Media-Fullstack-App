package com.revature.exceptions;

public class EmailAlreadyExists extends Exception  {
    public EmailAlreadyExists(String msg) {
        super(msg);
    }
}
