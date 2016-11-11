package com.nwords;

import com.nwords.interactors.IUInteractor;
import com.nwords.interactors.TrainingInteractor;
import com.nwords.ui.CLI;

public class UIGetter {

    private static IUInteractor CLI;

    public static IUInteractor CLI(TrainingInteractor i) {
        CLI cl = new CLI(i);
        return cl;
    }
}
