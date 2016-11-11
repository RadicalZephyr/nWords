package com.nwords;

import com.nwords.interactors.IDB;
import com.nwords.interactors.IUInteractor;
import com.nwords.interactors.TrainingInteractor;

import java.sql.SQLException;

public class Main {

    public static void setupAndRun() throws Exception {
        // dependency injections

        IDB storage = StorageGetter.mysqlDB();
        TrainingInteractor training = new TrainingInteractor(storage);
        IUInteractor ui = UIGetter.CLI(training);
        ui.run();
    }

    public static void main(String[] args) throws Exception {
        setupAndRun();
    }
}
