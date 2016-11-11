package com.nwords.entity;

import java.time.LocalDateTime;

public class WordEntity extends VocabulatyEntity {

    int id;
    String word;
    String translation;
    String type;
    String domain;
    int attempts;
    int chapter;
    LocalDateTime lastTrainedDate;


    public WordEntity(int id, String word, String translation, String type, String domain) {

        this.id = id;
        this.word = word;
        this.translation = translation;
        this.type = type;
        this.domain = domain;

    }

    public WordEntity(int id, String word, String translation, String type, String domain, int attempts,
                      LocalDateTime lastTrainedAttempt, int chapter) {

        this.id = id;
        this.word = word;
        this.translation = translation;
        this.type = type;
        this.domain = domain;
        this.attempts = attempts;
        this.lastTrainedDate = lastTrainedAttempt;
        this.chapter = chapter;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return this.word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public LocalDateTime getLastTrainedDate() {
        return lastTrainedDate;
    }

    public void setLastTrainedDate(LocalDateTime lastTrainedAttempt) {
        this.lastTrainedDate = lastTrainedAttempt;
    }
}
