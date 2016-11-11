package com.nwords.database.database;

import com.nwords.interactors.IDB;
import com.nwords.database.CategoryRecord;
import com.nwords.database.WordRecord;

import java.sql.*;
import java.util.ArrayList;

public class MySqlDB implements IDB {

    private Connection conn;

    public MySqlDB(Connection c) {
        conn = c;
    }

    private ArrayList<WordRecord> convertToWordRecordsAndClose(ResultSet rs) throws Exception {
        ArrayList<WordRecord> records = new ArrayList<>();

        while (rs.next()) {
            WordRecord word = new WordRecord();
            word.id = rs.getInt(1);
            word.word = rs.getString(2);
            word.translation = rs.getString(3);
            word.type = rs.getString(4);
            word.category = getWordCategoryByID(rs.getInt(5));
            word.numOfSuccessfullAttempts = rs.getInt(6);
            word.lastTrainedDate = rs.getTimestamp(7);
            records.add(word);
        }
        return records;
    }

    private String getWordCategoryByID(int id) throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String selectCategoryNameStr = "select category from domain where id = ?";
        PreparedStatement selectCategoryName = conn.prepareStatement(selectCategoryNameStr);
        selectCategoryName.setInt(1, id);
        ResultSet rs  = selectCategoryName.executeQuery();
        rs.next();
        String name  = rs.getString(1);
        rs.close();

        return name;
    }

    public ArrayList<WordRecord> getWordsByCategory(int category) throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String selectWordsQuery = "select id, ned, eng, word_type, category, attempts, last_trained_date from words where category = ?";

        PreparedStatement selectWords = conn.prepareStatement(selectWordsQuery);
        selectWords.setInt(1, category);

        ResultSet rs = selectWords.executeQuery();
        return convertToWordRecordsAndClose(rs);
    }

    public ArrayList<WordRecord> getWordsByType(String type) throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String selectWordsQuery = "select id, ned, eng, word_type, category, attempts, last_trained_date from words where word_type = ?";
        PreparedStatement selectWords = conn.prepareStatement(selectWordsQuery);
        selectWords.setString(1, type);

        ResultSet rs = selectWords.executeQuery();
        return convertToWordRecordsAndClose(rs);

    }

    public ArrayList<WordRecord> getWordsByCategoryAndType(int category, String type) throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String selectWordsQuery = "select id, ned, eng, word_type, category, attempts, last_trained_date from words where category = ? and word_type = ?";
        PreparedStatement selectWords = conn.prepareStatement(selectWordsQuery);

        selectWords.setInt(1, category);
        selectWords.setString(2, type);

        ResultSet rs = selectWords.executeQuery();
        return convertToWordRecordsAndClose(rs);
    }

    public ArrayList<WordRecord> getWordsAll() throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String selectWordsQuery = "select id, ned, eng, word_type, category, attempts, last_trained_date from words";
        PreparedStatement selectWords = conn.prepareStatement(selectWordsQuery);

        ResultSet rs = selectWords.executeQuery();
        return convertToWordRecordsAndClose(rs);
    }

    public ArrayList<CategoryRecord> getCategoryNames() throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String selectWordsQuery = "select id, category from domain";
        PreparedStatement selectWords = conn.prepareStatement(selectWordsQuery);

        ResultSet rs = selectWords.executeQuery();
        ArrayList<CategoryRecord> categories = new ArrayList<>();
        while (rs.next()) {
            CategoryRecord cr = new CategoryRecord();
            cr.id = rs.getInt(1);
            cr.name = rs.getString(2);
            categories.add(cr);
        }
        return categories;
    }

    public void updateWords(ArrayList<WordRecord> words) throws Exception {
        if (conn == null) throw new Exception("No connection to the database");

        String updateWordQuery = "update words set attempts = ? where id = ?";
        PreparedStatement updateWordStmt = conn.prepareStatement(updateWordQuery);

        for (WordRecord w : words) {
            updateWordStmt.setInt(1, w.numOfSuccessfullAttempts);
            updateWordStmt.setInt(2, w.id);
            updateWordStmt.executeUpdate();
        }
        updateWordStmt.close();
    }

    public void close() throws Exception {
        conn.close();
    }
}
