package com.game.bullseye.util;

import static com.game.bullseye.BullsEyeImplementation.NUMBER_OF_DIGITS_IN_THE_NUMBER;

public class GameMessages {

    private static final String GAME_NAME = "BullsEye";
    private static final String START_GAME = "Do You Want to Start the game?";
    private static final String ANOTHER_PLAY = "Do you wish to play again?";
    private static final String WRONG_INPUT = "Wrong input";
    private static final String BAD_INPUT = "Bad Input";
    private static final String NUMBER_CANT_BE_LESS_THEN = "Number cant be less then " + NUMBER_OF_DIGITS_IN_THE_NUMBER + " digits long";
    private static final String NUMBER_CANT_BE_MORE_THEN = "Number cant be more that " + NUMBER_OF_DIGITS_IN_THE_NUMBER + "digits long";
    private static final String ONLY_NUMBERS = "Please Use Only Numbers";
    private static final String DUPLICATED_NUMBERS = "Please Dont Use The Same Numbers";

    public static final Messages DO_YOU_WANT_TO_PLAY_THE_GAME = new Messages.MessagesBuilder()
            .setTitle(GAME_NAME)
            .setHeader(START_GAME).build();

    public static final Messages PLAY_AGAIN = new Messages.MessagesBuilder()
            .setTitle(GAME_NAME).
            setHeader(ANOTHER_PLAY).build();

   public static final Messages USE_ONLY_NUMBERS = new Messages.MessagesBuilder()
           .setTitle(BAD_INPUT)
           .setHeader(WRONG_INPUT)
           .setContent(ONLY_NUMBERS).build();

   public static final Messages NEED_MORE_DIGITS = new Messages.MessagesBuilder()
           .setTitle(BAD_INPUT)
           .setHeader(WRONG_INPUT)
           .setContent(NUMBER_CANT_BE_LESS_THEN).build();

    public static final Messages NEED_LESS_DIGITS = new Messages.MessagesBuilder()
            .setTitle(BAD_INPUT)
            .setHeader(WRONG_INPUT)
            .setContent(NUMBER_CANT_BE_MORE_THEN).build();

    public static final Messages ORIGINAL_NUMBERS = new Messages.MessagesBuilder()
            .setTitle(BAD_INPUT)
            .setHeader(WRONG_INPUT)
            .setContent(DUPLICATED_NUMBERS).build();



    }
