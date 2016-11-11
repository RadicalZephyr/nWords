package com.nwords.exceptions;

public class NotEnoughDataException extends IllegalArgumentException{

    public NotEnoughDataException() {
        super();
    }

    public NotEnoughDataException(String s) {
        super(s);
    }
}
