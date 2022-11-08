package com.game.bullseye.enums;

public enum ConfirmationCode {
    PLAY_THE_GAME(1),
    PLAY_THE_GAME_AGAIN(2),
    EXIT_THE_GAME(3),
    EXIT_THE_ROUND(4);

    private final int value;

    ConfirmationCode(int value) {this.value = value;
    }
}
