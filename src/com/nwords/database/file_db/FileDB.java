package com.nwords.database.file_db;

import com.nwords.interactors.IDB;
import com.nwords.database.CategoryRecord;
import com.nwords.database.WordRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDB implements IDB {

    private File f;
    private ArrayList<WordRecord> records;

    public FileDB(File file) {
        f = file;
        records = new ArrayList<>();

        try {
            Scanner in = new Scanner(new FileReader(f));
            int size = in.nextInt();
            int i = 0;
            while (i < size) {
                // System.out.println(i);
                WordRecord wr = new WordRecord();

                String str = in.next();

                wr.word = "";
                while (!str.equals(",")) {
                    wr.word += str + " ";
                    str = in.next();
                }
                str = in.next();
                wr.word = wr.word.substring(0, wr.word.length() - 1);

                // System.out.println(wr.word);

                wr.translation = "";
                while (!str.equals(",")) {
                    wr.translation += str + " ";
                    str = in.next();
                }
                str = in.next();
                wr.translation = wr.translation.substring(0, wr.translation.length() - 1);
                // System.out.println(wr.translation);

                wr.category = "";
                while (!str.equals(",")) {
                    wr.category += str;
                    str = in.next();
                }
                str = in.next();
                //System.out.println(wr.category);

                wr.type = "";
                while (!str.equals(",")) {
                    wr.type += str;
                    str = in.next();
                }
                str = in.next();
                // System.out.println(wr.type);

                while (!str.equals(",")) {
                    wr.chapter = Integer.parseInt(str);
                    str = in.next();
                }
                str = in.next();
                // System.out.println(wr.word + " " + wr.chapter);

                while (!str.equals(",")) {
                    wr.numOfSuccessfullAttempts = Integer.parseInt(str);
                    str = in.next();
                }
                str = in.next();

                // System.out.println(wr.numOfSuccessfullAttempts);
                String dateStr = "";
                while (!str.equals(";")) {
                    dateStr += str + " ";
                    str = in.next();
                }
                //System.out.print(wr.word + " ");
                //System.out.println(dateStr);
                wr.lastTrainedDate = Timestamp.valueOf(dateStr);

                //System.out.println(wr.lastTrainedDate);

                records.add(wr);
                i++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the file");
            e.printStackTrace();
        }

    }

    public void printWords() {
        for (WordRecord w : records) {
            System.out.println(w.word + " " + w.translation + " " + w.category + " " + w.type + " " + w.chapter + " " +
            w.numOfSuccessfullAttempts + " " + w.lastTrainedDate);
        }
    }

    public ArrayList<Object[]> getWords() {
        return new ArrayList<>();
    }


    public ArrayList<WordRecord> getWordsByCategory(int category) throws Exception {
        //printWords();
        ArrayList<WordRecord> filtered = new ArrayList<>();
        for (WordRecord w: records) {
            if (w.category.equals(category)) filtered.add(w);
        }
        return filtered;
    }

    public ArrayList<WordRecord> getWordsByType(String type) throws Exception {
        //printWords();
        ArrayList<WordRecord> filtered = new ArrayList<>();
        for (WordRecord w: records) {
            if (w.type.equals(type)) filtered.add(w);
        }
        return filtered;
    }

    public ArrayList<WordRecord> getWordsByCategoryAndType(int category, String type) throws Exception {
        //printWords();
        ArrayList<WordRecord> filtered = new ArrayList<>();
        for (WordRecord w: records) {
            if (w.category.equals(category) && w.type.equals(type)) filtered.add(w);
        }
        return filtered;
    }

    public ArrayList<WordRecord> getWordsAll() throws Exception {
        //printWords();
        return records;
    }

    public ArrayList<CategoryRecord> getCategoryNames() throws Exception {
        return null;
    }

    public void updateWords(ArrayList<WordRecord> words) {

    }

    public void close() throws Exception {
    }
}
