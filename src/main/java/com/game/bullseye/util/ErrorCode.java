package com.game.bullseye.util;

public enum ErrorCode {
    TO_FEW_DIGITS_IN_THE_NUMBER(1),
    TO_MANY_DIGITS_INT_THE_NUMBER(2),
    NOT_A_NUMBER_IN_THE_INPUT(3),
    DUPLICATED_NUMBER_IN_THE_INPUT(4);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
