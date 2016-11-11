package com.nwords.entity.word_types;

public enum Singularity {

    s ("singular"),
    p ("plural"),
    u ("uncountable");

    private final String text;

    Singularity(String t) {
        this.text = t;
    }

    public String getText() {
        return this.text;
    }

    public String toString() {
        return this.text;
    }
}