package com.nwords.interactors;

import com.nwords.ui.Category;
import com.nwords.ui.Type;

import java.util.ArrayList;

public interface IUInteractor {

    void getHelp();
    void trainWordsAndUpdateAccordingResults() throws Exception;
    ArrayList<Category> getWordCategories() throws Exception;
    ArrayList<Type> getWordTypes() throws Exception;
    void exit() throws Exception;
    void run() throws Exception;

}
