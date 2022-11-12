package com.game.bullseye;

import com.game.bullseye.util.alert.ErrorMessagesAlert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.game.bullseye.constants.GameConstants.*;
import static com.game.bullseye.enums.ErrorCode.*;

/**
 * This Class has the logic of the game,its responsible for create a random number
 * Find the number of hits and the number of bulls,print and validate user input.
 */
public class BullsEyeImplementation {

    private final ErrorMessagesAlert errorMessagesAlert = new ErrorMessagesAlert();
    private int numberOfGuesses;
    private String[] randomNumber;
    private List<String> historyOfGuesses;

    //Constructor that initialize the game.
    public BullsEyeImplementation() {
        this.numberOfGuesses = 0;
        this.randomNumber = new String[NUMBER_OF_DIGITS_IN_THE_NUMBER];
        this.historyOfGuesses = new ArrayList<>();
    }

    public void restartTheGame() {
        this.numberOfGuesses = 0;
        this.historyOfGuesses.clear();
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    //incrementing user guesses by one.
    public void incrementByOneGuessUserTook() {
        this.numberOfGuesses++;
    }

    public String[] getRandomNumber() {
        return randomNumber;
    }

    public List<String> getHistoryOfGuesses() {
        return historyOfGuesses;
    }

    //creating a random number that have different digits.
    public void randomNumberCreation() {
        String randomizeNumber = "";
        boolean[] numbers = new boolean[NUMBER_RANGE];
        Random randomGenerator = new Random();
        while (randomizeNumber.length() != NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            int randomNumber = randomGenerator.nextInt(NUMBER_RANGE);
            if (!numbers[randomNumber]) {
                numbers[randomNumber] = true;
                randomizeNumber = randomizeNumber.concat(String.valueOf(randomNumber));
            }
        }
        this.randomNumber = randomizeNumber.split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
    }

    /**
     * This function is calculating the hits - number of the right digits in the right place.
     *
     * @param randomNumber - the random number the program created
     * @param userGuess    - the number user guessed.
     * @return number of the right hits.
     */
    public int findNumberOfHits(String[] randomNumber, String[] userGuess) {
        int numberOfHits = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS_IN_THE_NUMBER; i++) {
            for (int j = 0; j < NUMBER_OF_DIGITS_IN_THE_NUMBER; j++) {
                if (randomNumber[i].equals(userGuess[j]) && i != j)
                    numberOfHits++;
            }
        }
        return numberOfHits;
    }

    /**
     * This function is calculating the number of bulls - number of right digits - don't have to be
     * in the right place
     *
     * @param randomNumber - random number the program created
     * @param userGuess    - the number user guessed.
     * @return number of bulls.
     */
    public int findNumberOfBulls(String[] randomNumber, String[] userGuess) {
        int numberOfBulls = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS_IN_THE_NUMBER; i++) {
            if (randomNumber[i].equals(userGuess[i]))
                numberOfBulls++;
        }
        return numberOfBulls;
    }

    /*
        This function is printing the history of the user guesses.
     */
    public String printListOfGuesses() {
        if (historyOfGuesses == null)
            return "null";

        int iMax = historyOfGuesses.size() - 1;
        if (iMax == -1) //no guesses
            return "[]";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append('[');
            b.append(historyOfGuesses.get(i));
            if (i == iMax)
                return b.append(']').toString();
            b.append("],\n");
        }
    }

    /**
     * Check if the number is valid.
     *
     * @param input - the number user tried to guess
     * @return if it is a validated guess.
     */
    public boolean validateResult(String input) {

        if (input.length() < NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            return errorMessagesAlert.getAlert(TO_FEW_DIGITS_IN_THE_NUMBER);
        }
        if (input.length() > NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            return errorMessagesAlert.getAlert(TO_MANY_DIGITS_INT_THE_NUMBER);
        }
        if (!input.matches(ONLY_NUMBERS)) {
            return errorMessagesAlert.getAlert(NOT_A_NUMBER_IN_THE_INPUT);
        }
        if (!duplicatedNumberInTheInput(input)) {
            return errorMessagesAlert.getAlert(DUPLICATED_NUMBER_IN_THE_INPUT);
        }
        return true;
    }

    /*
        private function that check if we have more than one duplicate in the number
     */
    private boolean duplicatedNumberInTheInput(String input) {
        String[] split = input.split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
        boolean[] numbers = new boolean[NUMBER_RANGE];
        for (int i = 0; i < NUMBER_OF_DIGITS_IN_THE_NUMBER; i++) {
            if (!numbers[Integer.parseInt(split[i])]) {
                numbers[Integer.parseInt(split[i])] = true;
            } else
                return false;
        }
        return true;
    }
}

