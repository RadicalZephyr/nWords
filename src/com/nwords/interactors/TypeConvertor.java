package com.nwords.interactors;

import com.nwords.database.CategoryRecord;
import com.nwords.database.WordRecord;
import com.nwords.entity.CategoryEntity;
import com.nwords.entity.EntityArray;
import com.nwords.entity.WordEntity;
import com.nwords.exceptions.NotEnoughDataException;
import com.nwords.ui.Category;
import com.nwords.ui.Word;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TypeConvertor {

    public static EntityArray wordRecordsToEntities(ArrayList<WordRecord> records) throws NotEnoughDataException {

        EntityArray entityArray = new EntityArray();

        for (WordRecord wr : records) {

            LocalDateTime date = null;
            if (wr.lastTrainedDate != null) date = wr.lastTrainedDate.toLocalDateTime();

            WordEntity w = new WordEntity(wr.id, wr.word, wr.translation, wr.type, wr.category, wr.numOfSuccessfullAttempts,
                    date, wr.chapter);
            entityArray.add(w);
        }

        return entityArray;
    }

    public static ArrayList<WordRecord> wordEntitiesToWordRecords(EntityArray<WordEntity> entities) throws NotEnoughDataException {

        ArrayList<WordRecord> records = new ArrayList();

        for (WordEntity we : entities) {

            WordRecord wr = new WordRecord();
            wr.id = we.getId();
            wr.word = we.getWord();
            wr.translation = we.getTranslation();
            wr.category = we.getDomain();
            wr.chapter = we.getChapter();
            wr.type = we.getType();
            wr.numOfSuccessfullAttempts = we.getAttempts();

            records.add(wr);
        }

        return records;
    }

    public static ArrayList<Word> wordEntitiesToWords(EntityArray<WordEntity> entities) throws NotEnoughDataException {

        ArrayList<Word> words = new ArrayList<>();

        for (WordEntity we : entities) {

            Word w = new Word(we.getId(), we.getWord(), we.getTranslation(), we.getDomain(), we.getType(), we.getChapter(),
                    we.getAttempts());
            words.add(w);
        }

        return words;
    }

    public static EntityArray<WordEntity> wordsToWordEntities(ArrayList<Word> words) throws NotEnoughDataException {

        EntityArray<WordEntity> entities = new EntityArray<>();

        for (Word w : words) {

            WordEntity we = new WordEntity(w.id, w.word, w.translation, w.type, w.category, w.attempts, null, w.chapter);

            entities.add(we);
        }

        return entities;
    }

    public static EntityArray<CategoryEntity> categoryRecordsToEntities(ArrayList<CategoryRecord> records)
            throws NotEnoughDataException {

        EntityArray entityArray = new EntityArray();

        for (CategoryRecord cr : records) {

            CategoryEntity w = new CategoryEntity(cr.id, cr.name);
            entityArray.add(w);
        }

        return entityArray;
    }

    public static ArrayList<Category> categoryEntitiesToCategories(EntityArray<CategoryEntity> entities)
            throws NotEnoughDataException {

        ArrayList<Category> categories = new ArrayList<>();

        for (CategoryEntity ce : entities) {

            Category c = new Category(ce.getId(), ce.getName());
            categories.add(c);
        }

        return categories;
    }

    public static EntityArray<CategoryEntity> categoriesToCategoryEntities(ArrayList<Category> categories)
            throws NotEnoughDataException {

        EntityArray<CategoryEntity> entities = new EntityArray<>();

        for (Category c : categories) {

            CategoryEntity ce = new CategoryEntity(c.id, c.name);

            entities.add(ce);
        }

        return entities;
    }

    public static ArrayList<Category> categoryRecordsToCategories(ArrayList<CategoryRecord> records)
            throws NotEnoughDataException {

        ArrayList categories = new ArrayList();

        for (CategoryRecord cr : records) {

            Category c = new Category(cr.id, cr.name);
            categories.add(c);
        }

        return categories;
    }


}
