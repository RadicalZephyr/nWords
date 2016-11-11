package com.nwords;

import java.io.File;
import java.sql.SQLException;

import com.nwords.database.database.MySqlDB;
import com.nwords.database.file_db.FileDB;
import com.nwords.interactors.IDB;

public class StorageGetter {

    private static IDB fileVocabularyDB;
    private static IDB fileVerbsDB;
    private static IDB mysqlDB;
    private static IDB fileTestDB;
    private static final String rootpath = new File("").getAbsolutePath();

    public static IDB fileVocabularyDB() {
        if (fileVocabularyDB == null) {
            String path = rootpath.concat("/src/com/nwords/database/file_db/Vocabulary.txt");
            fileVocabularyDB = new FileDB(new File(path));
        }
        return fileVocabularyDB;
    }

    public static IDB fileVerbsDB() {
        if (fileVerbsDB == null) {
            String path = rootpath.concat("/src/com/nwords/database/file_db/Verbs.txt");
            fileVerbsDB = new FileDB(new File(path));
        }
        return fileVerbsDB;
    }

    public static IDB fileTestDB() {
        if (fileTestDB == null) {
            String path = rootpath.concat("/src/com/nwords/test/Test_Data_Vocabulary.txt");
            System.out.println(path);
            fileTestDB = new FileDB(new File(path));
        }
        return fileTestDB;
    }

    public static IDB mysqlDB() throws SQLException {
        if (mysqlDB == null) mysqlDB = new MySqlDB(ConnectionGetter.mysql());
        return mysqlDB;
    }
}
