package com.game.bullseye.constants;

/**
 * This class has the constants of the game.
 * if we want to chose the number of digits of the number we can do it from this calss,
 * we also can change the range of the number we pick from(set to 0-9 for now)
 * and this class also have the regex that we use for validation.
 */
public class GameConstants {
    public final static int NUMBER_OF_DIGITS_IN_THE_NUMBER = 4;
    public final static int NUMBER_RANGE = 10;
    public final static String ONLY_NUMBERS = "^[0-9]*$";

}
