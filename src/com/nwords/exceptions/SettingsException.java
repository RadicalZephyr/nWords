package com.nwords.exceptions;

public class SettingsException extends IllegalArgumentException {

    public SettingsException() {
        super();
    }

    public SettingsException(String s) {
        super(s);
    }
}
