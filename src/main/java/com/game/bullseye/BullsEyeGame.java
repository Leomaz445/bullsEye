package com.game.bullseye;

import com.game.bullseye.enums.InformationCode;
import com.game.bullseye.util.Messages;
import com.game.bullseye.util.alert.ConfirmationMessagesAlert;
import com.game.bullseye.util.alert.InformationMessagesAlert;
import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Optional;

import static com.game.bullseye.BullsEyeImplementation.NUMBER_OF_DIGITS_IN_THE_NUMBER;
import static com.game.bullseye.enums.ConfirmationCode.*;

public class BullsEyeGame extends Application {

    private final ConfirmationMessagesAlert confirmationMessagesAlert = new ConfirmationMessagesAlert();
    private final InformationMessagesAlert informationMessagesAlert = new InformationMessagesAlert();


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        do {
            if (confirmationMessagesAlert.getAlert(PLAY_THE_GAME)) {
                gameEngine();
            }
        } while (!confirmationMessagesAlert.getAlert(EXIT_THE_GAME));
    }

    private void gameEngine() {
        do {
            BullsEyeImplementation bullsEyeImplementation = new BullsEyeImplementation();
            System.out.println(Arrays.toString(bullsEyeImplementation.getRandomNumber()));
            startToGuess(bullsEyeImplementation);
        } while (confirmationMessagesAlert.getAlert(PLAY_THE_GAME_AGAIN));
    }

    private void startToGuess(BullsEyeImplementation bullsEyeImplementation) {
        TextInputDialog dialog = new TextInputDialog();
        int bullsCounter = 0;
        while (bullsCounter != NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            dialog.setHeaderText("Please enter your guess");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (bullsEyeImplementation.validateResult(result.get())) {
                    bullsCounter = getBullsCounter(bullsEyeImplementation, result.get());
                }
            } else if (confirmationMessagesAlert.getAlert(EXIT_THE_GAME)) {
                break;
            }
        }
        if (bullsCounter == NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            informationMessagesAlert.getAlert(InformationCode.SUMMERY, new Messages.MessagesBuilder()
                    .setTitle("Good Job!")
                    .setHeader("You successfully guessed the number")
                    .setContent("The number of valid guess it took you is" + bullsEyeImplementation.getTotalNumberOfGuess()).build());
        }
    }

    private int getBullsCounter(BullsEyeImplementation bullsEyeImplementation, String result) {
        int bullsCounter;
        int histCounter;
        bullsEyeImplementation.incrementByOneGuessUserTook();
        String[] split = result.split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
        bullsCounter = bullsEyeImplementation.findNumberOfBulls(bullsEyeImplementation.getRandomNumber(), split);
        histCounter = bullsEyeImplementation.findNumberOfHits(bullsEyeImplementation.getRandomNumber(), split);
        String resultOfTheGuess = String.format("You guess is: " + result + " You got %s bulls and %s hits ", bullsCounter, histCounter);
        bullsEyeImplementation.getHistoryOfGuesses().add(resultOfTheGuess);
        informationMessagesAlert.getAlert(InformationCode.YOUR_RESULT, new Messages.MessagesBuilder()
                .setHeader("Your Result")
                .setHeader(resultOfTheGuess)
                .setContent(bullsEyeImplementation.printListOfGuesses()).build());
        return bullsCounter;
    }
}