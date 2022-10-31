package com.game.bullseye;

import com.game.bullseye.util.alert.ErrorMessagesAlert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.game.bullseye.constants.GameConstants.*;
import static com.game.bullseye.enums.ErrorCode.*;

public class BullsEyeImplementation {

    private final ErrorMessagesAlert errorMessagesAlert = new ErrorMessagesAlert();
    private int validGuess;
    private int invalidGuess;
    private String[] randomNumber;
    private List<String> historyOfGuesses;

    public BullsEyeImplementation() {
        this.validGuess = 0;
        this.invalidGuess = 0;
        this.randomNumber = new String[NUMBER_OF_DIGITS_IN_THE_NUMBER];
        this.historyOfGuesses = new ArrayList<>();
    }

    public int getTotalNumberOfGuess() {
        return validGuess + invalidGuess;
    }

    public void incrementByOneGuessUserTook() {
        this.validGuess++;
    }

    public String[] getRandomNumber() {
        return randomNumber;
    }

    public List<String> getHistoryOfGuesses() {
        return historyOfGuesses;
    }


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
        this.randomNumber=randomizeNumber.split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
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
        invalidGuess++;
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

