package com.nwords.interactors;

import com.nwords.database.CategoryRecord;
import com.nwords.entity.EntityArray;
import com.nwords.entity.WordEntity;
import com.nwords.entity.WordType;
import com.nwords.ui.Category;
import com.nwords.ui.Parameters;
import com.nwords.ui.Type;
import com.nwords.ui.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class TrainingInteractor {

    private IDB storage;
    private TrainingOperations trainingOps;

    public TrainingInteractor(IDB storage) {
        this.storage = storage;
        this.trainingOps = new TrainingOperations();
    }

    public ArrayList<Word> getWordsForTraining(Parameters p) throws Exception {
        WordType wordType = null;
        try {
            WordType.valueOf(p.wordType);
        } catch (java.lang.IllegalArgumentException e) {
        }
        TrainingParameters params = new TrainingParameters(p.maxNumOfWords, p.category, wordType);
        return TypeConvertor.wordEntitiesToWords(trainingOps.getWordsForTraining(params, storage));
    }

    public ArrayList<Type> getWordTypes() {
        ArrayList<Type> types = new ArrayList<>();
        for (WordType wt : WordType.values()) {
            Type t = new Type(wt.value(), wt.toString());
            types.add(t);
        }

        return types;
    }

    public void updateWords(ArrayList<Word> words) throws Exception {
        EntityArray<WordEntity> wordEntities = TypeConvertor.wordsToWordEntities(words);
        trainingOps.updateWords(wordEntities, storage);
    }

    public void updateWord(Word w) throws Exception {
        ArrayList<Word> words = new ArrayList<>();
        words.add(w);
        updateWords(words);
    }

    public ArrayList<Category> getWordCategories() throws Exception {
        ArrayList<CategoryRecord> categories = storage.getCategoryNames();
        return TypeConvertor.categoryRecordsToCategories(categories);
    }

    public void exit() throws Exception {
        try {
            storage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
