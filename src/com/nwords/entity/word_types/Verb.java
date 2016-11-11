package com.nwords.entity.word_types;

public class Verb extends Word {

    private Singularity singulatity;
    private Gender gender;
    private Verb infinitive;

    public Verb(String[] word, String translation, int wordTypeIndex, Gender g, Singularity s, Verb inf) {
        super(word, translation, wordTypeIndex);
        gender = g;
        singulatity = s;
        infinitive = inf;
    }

    public String getSingularity(){
        return singulatity.getText();
    }

    public String getGender(){
        return gender.getText();
    }

    public String[] getInfinitive(){
        return infinitive.getWord();
    }

    public String toString() {
        return super.getWord() + " (" + super.getTranslation() + ", " + gender + ", " + singulatity + ")";
    }
}
