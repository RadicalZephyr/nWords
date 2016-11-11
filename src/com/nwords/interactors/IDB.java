package com.nwords.interactors;

import com.nwords.database.CategoryRecord;
import com.nwords.database.WordRecord;

import java.util.ArrayList;

public interface IDB {


    ArrayList<WordRecord> getWordsByCategory(int category) throws Exception;
    ArrayList<WordRecord> getWordsByType(String type) throws Exception;
    ArrayList<WordRecord> getWordsByCategoryAndType(int category, String type) throws Exception;
    ArrayList<WordRecord> getWordsAll() throws Exception;
    ArrayList<CategoryRecord> getCategoryNames() throws Exception;
    void updateWords(ArrayList<WordRecord> words) throws Exception;
    void close() throws Exception;
}
