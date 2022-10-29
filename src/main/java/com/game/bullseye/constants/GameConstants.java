package com.game.bullseye.constants;

public class GameConstants {
    private final int NUMBER_OF_DIGITS_IN_THE_NUMBER = 4;
    private final int NUMBER_RANGE = 10;
    private final String ALPHABET_REGEX = ".*[a-zA-Z]+.*";

    public int getNUMBER_OF_DIGITS_IN_THE_NUMBER() {
        return NUMBER_OF_DIGITS_IN_THE_NUMBER;
    }

    public int getNUMBER_RANGE() {
        return NUMBER_RANGE;
    }

    public String getALPHABET_REGEX() {
        return ALPHABET_REGEX;
    }
}
