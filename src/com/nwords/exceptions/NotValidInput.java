package com.nwords.exceptions;

public class NotValidInput extends RuntimeException{

    public NotValidInput() {
        super();
    }

    public NotValidInput(String s) {
        super(s);
    }
}
