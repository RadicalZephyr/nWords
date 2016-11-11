package com.nwords.test;


import com.nwords.interactors.IDB;
import com.nwords.StorageGetter;
import org.junit.Test;

public class TestMySqlDB {

    @Test
    public void testConnection() throws Exception{
        StorageGetter.mysqlDB();
    }

    @Test
    public void testGetWords() throws Exception{
        IDB db = StorageGetter.mysqlDB();
        db.getWordsAll();
    }
}
