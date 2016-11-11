package com.nwords.entity.word_types;

import com.nwords.entity.WordType;

public abstract class Word {

    private String[] word;
    private String translation;
    private WordType type;

    public Word(String[] w, String t, int wordTypeIndex){
        word = w;
        translation = t;
        type = type.getWordType(wordTypeIndex);
    }

    /*
    public String toString() {
        return word + " ( " + translation + " )";
    }*/

    public String[] getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getWordType() {
        return type.toString();
    }

    public String toString() {
        String ans = "";
        for (String s: word) ans = ans + " " + s;

        return ans;
    }
}