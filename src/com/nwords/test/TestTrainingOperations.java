package com.nwords.test;

import com.nwords.interactors.IDB;
import com.nwords.StorageGetter;
import com.nwords.interactors.TrainingOperations;
import com.nwords.interactors.TrainingParameters;
import com.nwords.entity.WordType;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestTrainingOperations {

    @Test
    public void TestDaysGapForZeroAttempt() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 0;
        assertEquals(0, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForOneAttempt() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 1;
        assertEquals(0, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForTwoAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 2;
        assertEquals(1, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForThreeAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 3;
        assertEquals(1, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForFourAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 4;
        assertEquals(2, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForFiveAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 5;
        assertEquals(2, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForSixAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 6;
        assertEquals(4, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForSevenAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 7;
        assertEquals(4, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForEightAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 8;
        assertEquals(8, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForNineAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 9;
        int days = ops.getDaysGapBetweenSessions(attempts);
        assertEquals(8, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestDaysGapForTenAttempts() throws Exception {
        TrainingParameters params = new TrainingParameters(100, 1, WordType.noun);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        int attempts = 10;
        int days = ops.getDaysGapBetweenSessions(attempts);
        assertEquals(16, ops.getDaysGapBetweenSessions(attempts));
    }

    @Test
    public void TestTrainingOnFileDB() throws Exception {
        TrainingParameters params = new TrainingParameters(100, null, null);
        IDB storage = StorageGetter.fileTestDB();

        TrainingOperations ops = new TrainingOperations();
        ops.getWordsForTraining(params, storage);
    }

    @Test
    public void TestTrainingOnMysqlDB() throws Exception {
        TrainingParameters params = new TrainingParameters(10, null, null);
        IDB storage = StorageGetter.mysqlDB();

        TrainingOperations ops = new TrainingOperations();
        ops.getWordsForTraining(params, storage);
    }
}
