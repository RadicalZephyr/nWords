package com.nwords.entity.word_types;

public class Pronoun extends Word {

    Singularity singulatity;
    Gender gender;
    String article;

    public Pronoun(String[] word, String translation, int wordTypeIndex, String a, Gender g, Singularity s) {
        super(word, translation, wordTypeIndex);
        article = a;
        gender = g;
        singulatity = s;
    }

    public String toString() {
        return article + " " + super.getWord() + " (" + super.getTranslation() + ", " + gender + ", " + singulatity + ")";
    }
}