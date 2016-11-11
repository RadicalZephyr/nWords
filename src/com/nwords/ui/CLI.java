package com.nwords.ui;

import com.nwords.exceptions.NotValidInput;
import com.nwords.interactors.IDB;
import com.nwords.interactors.IUInteractor;
import com.nwords.Vocabulary;
import com.nwords.interactors.TrainingInteractor;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI implements IUInteractor {

    private TrainingInteractor interactor;
    private int DEFAULT_MAX_NUM_OF_WORDS = 20;

    public CLI (TrainingInteractor i) {
        this.interactor = i;
    }

    public static void trainVocabulary(int reverseTranslation, IDB db) {
        // eng to ned (0) or ned to end (1)
        Vocabulary.trainVocabulary(db, reverseTranslation);
    }

    public void getHelp() {

        System.out.println("To train English to Nederlands translation: type '/train'");
        System.out.println("To see this info again: type '/help'");
        System.out.println("Exit: type '/exit'");
        System.out.println();
    }

    private Integer getMaxNumOfWordsFromUser() throws Exception {
        System.out.println("\nEnter the maximum amount of words to train or " +
                           "press ENTER to use default value (20)\n");
        Integer maxNum = DEFAULT_MAX_NUM_OF_WORDS;
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        if (userInput.equals("/exit")) exit();
        else if (userInput.equals("/help")) getHelp();
        else if (userInput.equals("")) return maxNum;
        else {
            try {
                maxNum = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                if (e instanceof NumberFormatException)
                    throw new NotValidInput("You entered " + userInput + " instead of a number");
                else e.printStackTrace();
            }
        }
        return maxNum;
    }

    private Integer getCategoryFromUser() throws Exception, NotValidInput {
        System.out.println("\nEnter the number of the word domain or press ENTER to train all domains\n");
        ArrayList<Category> categories = getWordCategories();
        for (Category c : categories) {
            System.out.println(c.id + " : " + c.name);
        }
        System.out.println();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Integer cat = null;

        if (userInput.equals("/exit")) exit();
        else if (userInput.equals("/help")) getHelp();
        else if (userInput.equals("")) return cat;
        else {
            try {
                cat = Integer.parseInt(userInput);
                categories.get(cat);
            } catch (Exception e) {
                if (e instanceof NumberFormatException)
                    throw new NotValidInput("You entered " + userInput + " instead of a number");
                else if (e instanceof IndexOutOfBoundsException)
                    throw new NotValidInput("The number you entered in not in the list:  " + userInput + " instead of a number");
                else e.printStackTrace();
            }
        }
        return cat;
    }

    private String getWordTypeFromUser() throws Exception {
        System.out.println("\nEnter the number of the word type or press ENTER to train all types\n");
        ArrayList<Type> types = getWordTypes();
        String type = "";
        for (Type t : types) {
            System.out.println(t.id + " : " + t.name);
        }
        System.out.println();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        if (userInput.equals("/exit")) exit();
        else if (userInput.equals("/help")) getHelp();
        else if (userInput.equals("")) return type;
        else {
            try {
                int chosenType = Integer.parseInt(userInput);
                type = types.get(chosenType).name;
            } catch (Exception e) {
                if (e instanceof NumberFormatException)
                    throw new NotValidInput("You entered " + userInput + " instead of a number");
                else if (e instanceof IndexOutOfBoundsException)
                    throw new NotValidInput("The number you entered in not in the list:  " + userInput + " instead of a number");
                else e.printStackTrace();
            }
        }
        //in.close();
        return type;
    }

    public Parameters getTrainingParameters() throws Exception, NotValidInput {
        Integer maxNumOfWords = getMaxNumOfWordsFromUser();
        Integer cat = getCategoryFromUser();
        String wordType = getWordTypeFromUser();
        Parameters p = new Parameters(maxNumOfWords, cat, wordType);
        return p;
    }

    public ArrayList<Category> getWordCategories() throws Exception {
        return interactor.getWordCategories();
    }

    public ArrayList<Type> getWordTypes() throws Exception {
        return interactor.getWordTypes();
    }

    public void exit() throws Exception {
        interactor.exit();
        System.exit(0);
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

    private double trainAWord(Word w) {

        int totalNumOfTrials = 3;
        int attempt = checkSpelling(totalNumOfTrials, w.translation, w.word);
        return (double) attempt/totalNumOfTrials;
    }

    public void trainWordsAndUpdateAccordingResults() throws Exception, NotValidInput {
        // ask for training settings and get words for training
        ArrayList<Word> words = interactor.getWordsForTraining(getTrainingParameters());
        // repeat for every word :
        int totalNumOfTrials = 3;
        double score = 0;
        double wordsTrained = 0;
        for (Word w : words) {
            double current_score =  trainAWord(w);
            if (current_score < 1) w.setAttemptsToZero();
            else w.incrementAttempts();
            score += current_score;
            wordsTrained++;
            interactor.updateWord(w);
        }
        System.out.println("Your score is: " + score + " / " + wordsTrained);
    }

    public void run() throws Exception {
        getHelp();
        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine().toLowerCase();

            if (command.equals("/exit")) exit();
            else if (command.equals("/help")) getHelp();
            else if (command.equals("/train")) trainWordsAndUpdateAccordingResults();
                //else if (command.equals("/eng")) trainVocabulary(0, StorageGetter.mysqlDB());
                //else if (command.equals("/ned")) trainVocabulary(1, StorageGetter.mysqlDB());
                //else if (command.equals("/verbs")) trainVocabulary(1, StorageGetter.mysqlDB());
            else System.out.println(command);
            System.out.println();
        }

    }
}
