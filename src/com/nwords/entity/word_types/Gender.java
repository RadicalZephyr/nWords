package com.nwords.entity.word_types;

public enum Gender {

    m ("male"),
    f ("female"),
    all ("all"),
    none ("none");

    private final String text;

    Gender(String t) {
        this.text = t;
    }

    public String getText() {
        return this.text;
    }

    public String toString() {
        return this.text;
    }
}

