package com.nwords.database;

import java.sql.Timestamp;

public class WordRecord {

    public int id;
    public String word;
    public String translation;
    public String category;
    public String type;
    public int numOfSuccessfullAttempts;
    public Timestamp lastTrainedDate;
    public int chapter;

    public WordRecord() {

    }

}
