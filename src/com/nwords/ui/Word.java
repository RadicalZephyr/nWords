package com.nwords.ui;

public class Word {

    public final int id;
    public final String word;
    public final String translation;
    public final String category;
    public final String type;
    public final int chapter;
    public int attempts;

    public Word(int id, String word, String translation, String category, String type, int chapter, int attempts) {
        this.id = id;
        this.word = word;
        this.translation = translation;
        this.category = category;
        this.type = type;
        this.chapter = chapter;
        this.attempts = attempts;
    }

    public void setAttemptsToZero() {
        this.attempts = 0;
    }

    public void incrementAttempts() {
        this.attempts += 1;
    }
}
