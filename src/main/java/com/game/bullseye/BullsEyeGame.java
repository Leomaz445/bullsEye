package com.game.bullseye;

import com.game.bullseye.util.Messages;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

import static com.game.bullseye.BullsEyeImplementation.NUMBER_OF_DIGITS_IN_THE_NUMBER;
import static com.game.bullseye.util.GameMessages.DO_YOU_WANT_TO_PLAY_THE_GAME;
import static com.game.bullseye.util.GameMessages.PLAY_AGAIN;

public class BullsEyeGame extends Application {
    private final Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        do {
            Optional<ButtonType> startGame = getAlertConfirmation(alertConfirmation, DO_YOU_WANT_TO_PLAY_THE_GAME);
            if (startGame.isPresent())
                if (startGame.get() == ButtonType.OK) {
                    gameEngine();
                } else if (startGame.get() == ButtonType.CLOSE) {
                    confirmExitMessage();
                }
        } while (confirmExitMessage());
    }

    private boolean confirmExitMessage() {
        alertConfirmation.setTitle("Text for title");
        alertConfirmation.setHeaderText("Text for header");
        alertConfirmation.setContentText("text");
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        if (option.get() == ButtonType.OK)
            return true;

        return false;
    }


    private void gameEngine() {
        boolean playing = true;
        while (playing) {
            BullsEyeImplementation bullsEyeImplementation = new BullsEyeImplementation();
            startTheGame(bullsEyeImplementation);
            summeryMessageToTheUser(alertInformation, bullsEyeImplementation);
            playing = checkIfUserWantToPlayAgain(playing, alertConfirmation);
        }
    }

    private void startTheGame(BullsEyeImplementation bullsEyeImplementation) {
        TextInputDialog dialog = new TextInputDialog();
        int bullsCounter = 0;
        while (bullsCounter != NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            dialog.setHeaderText("Please enter your guess");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (bullsEyeImplementation.validateResult(result.get())) {
                    bullsCounter = getBullsCounter(alertInformation, bullsEyeImplementation, result);
                }
            } else {
                break;
            }
        }
    }

    private void summeryMessageToTheUser(Alert alertInformation, BullsEyeImplementation bullsEyeImplementation) {
//        getAlertInformation(alertInformation, "Good Job!", "You successfully guessed the number",
//                "Excellent Job total number of good guesses is: " + bullsEyeImplementation.getGuessUserTook());
    }

    private boolean checkIfUserWantToPlayAgain(boolean playing, Alert alertConfirmation) {
        Optional<ButtonType> option = getAlertConfirmation(alertConfirmation, PLAY_AGAIN);
        if (option.get().getButtonData() == ButtonType.CLOSE.getButtonData()) {
            playing = false;
        }
        return playing;
    }

    private int getBullsCounter(Alert alertInformation, BullsEyeImplementation bullsEyeImplementation, Optional<String> result) {
        int bullsCounter;
        int histCounter;
        bullsEyeImplementation.incrementByOneGuessUserTook();
        String[] split = result.get().split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
        bullsCounter = bullsEyeImplementation.findNumberOfBulls(bullsEyeImplementation.getRandomNumber(), split);
        histCounter = bullsEyeImplementation.findNumberOfHits(bullsEyeImplementation.getRandomNumber(), split);
        String format = String.format("You guessed: " + result.get() + " You got %s bulls and %s hits ", bullsCounter, histCounter);
        bullsEyeImplementation.getHistoryOfGuesses().add(format);
//        getAlertInformation(alertInformation, "Your Result", format, bullsEyeImplementation.printListOfGuesses());
        return bullsCounter;
    }

    public static Optional<ButtonType> getAlertConfirmation(Alert alertConfirmation, Messages messages) {
        alertConfirmation.setTitle(messages.getTitle());
        alertConfirmation.setHeaderText(messages.getHeader());
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        return option;
    }
}