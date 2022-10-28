package com.game.bullseye;

import com.game.bullseye.util.ErrorMessageConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.game.bullseye.util.ErrorCode.*;

public class BullsEyeImplementation {
    public static final int NUMBER_OF_DIGITS_IN_THE_NUMBER = 4;
    public static final int NUMBER_RANGE = 10;
    public static final String REGEX_ALPHABET_USED = ".*[a-zA-Z]+.*";
    private int guessUserTook;
    private String[] randomNumber;
    private List<String> historyOfGuesses;
    private ErrorMessageConstants errorMessageConstants = new ErrorMessageConstants();

    public BullsEyeImplementation() {
        this.guessUserTook = 0;
        this.randomNumber = randomNumberCreation();
        this.historyOfGuesses = new ArrayList<>();
    }

    public int getGuessUserTook() {
        return guessUserTook;
    }

    public void incrementByOneGuessUserTook() {
        this.guessUserTook++;
    }

    public String[] getRandomNumber() {
        return randomNumber;
    }

    public List<String> getHistoryOfGuesses() {
        return historyOfGuesses;
    }


    private String[] randomNumberCreation() {
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
        return randomizeNumber.split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
    }

    public int findNumberOfHits(String[] randomNumber, String[] split) {
        int numberOfHits = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS_IN_THE_NUMBER; i++) {
            for (int j = 0; j < NUMBER_OF_DIGITS_IN_THE_NUMBER; j++) {
                if (randomNumber[i].equals(split[j]) && i != j)
                    numberOfHits++;
            }

        }
        return numberOfHits;
    }

    public int findNumberOfBulls(String[] randomNumber, String[] userGuess) {
        int numberOfBulls = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS_IN_THE_NUMBER; i++) {
            if (Objects.equals(randomNumber[i], userGuess[i]))
                numberOfBulls++;
        }
        return numberOfBulls;
    }

    public String printListOfGuesses() {
        if (historyOfGuesses == null)
            return "null";

        int iMax = historyOfGuesses.size() - 1;
        if (iMax == -1)
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


    public boolean validateResult(String input) {

        if (input.length() < NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            return errorMessageConstants.createErrorOutPut(TO_FEW_DIGITS_IN_THE_NUMBER);
        }
        if (input.length() > NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            return errorMessageConstants.createErrorOutPut(TO_MANY_DIGITS_INT_THE_NUMBER);
        }
        if (input.matches(REGEX_ALPHABET_USED)) {
            return errorMessageConstants.createErrorOutPut(NOT_A_NUMBER_IN_THE_INPUT);
        }
        if (!duplicatedNumberInTheInput(input)) {
            return errorMessageConstants.createErrorOutPut(DUPLICATED_NUMBER_IN_THE_INPUT);
        }
        return true;
    }

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

