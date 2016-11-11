package com.nwords;

import com.nwords.database.WordRecord;
import com.nwords.interactors.IDB;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Vocabulary {

    private static void shuffle(ArrayList<WordRecord> words) {
        Random rand  = new Random();
        int size = words.size();
        while (size > 1) {
            int randId = rand.nextInt(size);
            WordRecord swap = words.get(randId);
            words.set(randId, words.get(size - 1));
            words.set(size-1, swap);
            size -= 1;
        }
    }

    private static int checkSpelling(int trials, String word, String translation) {
        if (trials < 1) {
            System.out.println("Incorrect. The correct answer is: " + translation);
            return trials;
        }
        System.out.println(word + " :");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals(translation)) {
            System.out.println("Correct!\n");
            return trials;
        }
        return checkSpelling(trials - 1, word, translation);
    }

    public static void trainVocabulary(IDB source, int column) {
        int totalNumOfTrials = 3;
        double points = 0;
        ArrayList<WordRecord> words = new ArrayList<>();
        try {
            words = source.getWordsAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shuffle(words);
        double wordsTrained = 0;
        for (WordRecord word : words) {
            int attempt = checkSpelling(totalNumOfTrials, word.translation, word.word);
            points += (double) attempt/totalNumOfTrials;
            wordsTrained++;
        }

        System.out.println("Points: " + points + "/" + wordsTrained);
    }

}
