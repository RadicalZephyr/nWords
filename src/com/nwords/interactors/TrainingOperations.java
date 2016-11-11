package com.nwords.interactors;

import com.nwords.database.WordRecord;
import com.nwords.entity.EntityArray;
import com.nwords.entity.WordEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.nwords.interactors.TypeConvertor.wordRecordsToEntities;

public class TrainingOperations {

    public int getDaysGapBetweenSessions(int attempts) {
        // RULES: spaced repetition (twice 1 day, then twice after 2 days, then twice after 4 days, etc.. days = powers of 2)
        // number of attempts - waiting days :
        // 0 - 0
        // 1 - 1
        // 2 - 1
        // ...
        // 8 - 8
        // 9 - 8
        // 10 - 16

        return (int) Math.pow(2, ((attempts / 2) - 1));
    }

    public EntityArray<WordEntity> filterByTrainingRules(EntityArray<WordEntity> words, TrainingParameters params) {

        int count = 0;

        EntityArray<WordEntity> filteredByRules = new EntityArray();

        for (WordEntity w : words) {
            //System.out.println(w.getAttempts());
            if (count >= params.maxAmountOfWords || count > words.size()) return filteredByRules;
            if (w.getAttempts() == 0) {
                filteredByRules.add(w);
            }
            else {
                int daysGap = getDaysGapBetweenSessions(w.getAttempts());
                LocalDate date = LocalDate.now().minusDays(daysGap);
                boolean ans = w.getLastTrainedDate().isBefore(date.atStartOfDay());
                //System.out.println(w.getLastTrainedDate());
                //System.out.println("date " + date);
                //System.out.println(w.getLastTrainedDate() + " is before " + date + ans);
                if (w.getLastTrainedDate().isBefore(date.atStartOfDay())) {
                    filteredByRules.add(w);
                }
            }
            count++;

        }
        return filteredByRules;
    }

    private EntityArray getWords(TrainingParameters params, IDB source) throws Exception {

        ArrayList<WordRecord> records = new ArrayList<>();

        try {
            if (params.domain != null && params.type != null)
                records = source.getWordsByCategoryAndType(params.domain, params.type.toString());
            else if (params.type != null) records = source.getWordsByType(params.type.toString());
            else if (params.domain != null) records = source.getWordsByCategory(params.domain);
            else records = source.getWordsAll();
        } catch (Exception e) {
            System.out.println("Cannot retrieve words from the database");
            e.printStackTrace();
        }

        return wordRecordsToEntities(records);
    }

    public void updateWords(EntityArray<WordEntity> wordEntities, IDB source) throws Exception {

        source.updateWords(TypeConvertor.wordEntitiesToWordRecords(wordEntities));
    }

    public EntityArray<WordEntity> getWordsForTraining(TrainingParameters params, IDB source) throws Exception {

        EntityArray<WordEntity> words = getWords(params, source);

        EntityArray<WordEntity> shuffledWords = words.getShuffledArray();

        return filterByTrainingRules(shuffledWords, params);
    }
}
