package com.game.bullseye;

import com.game.bullseye.enums.InformationCode;
import com.game.bullseye.util.Messages;
import com.game.bullseye.util.alert.ConfirmationMessagesAlert;
import com.game.bullseye.util.alert.InformationMessagesAlert;
import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

import static com.game.bullseye.constants.GameConstants.NUMBER_OF_DIGITS_IN_THE_NUMBER;
import static com.game.bullseye.enums.ConfirmationCode.*;

/**
 * The Main of the game, from here we managed the game - showing output messages,and using the implementation class/
 */
public class BullsEyeGame extends Application {

    private final ConfirmationMessagesAlert confirmationMessagesAlert = new ConfirmationMessagesAlert();
    private final InformationMessagesAlert informationMessagesAlert = new InformationMessagesAlert();


    public static void main(String[] args) {
        launch();
    }

    @Override
    /*
        Asking the player if he wants to start to play or if he wants to exit the game - we need a conformation.
     */
    public void start(Stage stage) {
        do {
            if (confirmationMessagesAlert.getAlert(PLAY_THE_GAME)) {
                gameEngine();
            }
        } while (!confirmationMessagesAlert.getAlert(EXIT_THE_GAME));
    }

    /**
     * While the user chose to play he is playing, before he  live we would ask him if he sure.
     */
    private void gameEngine() {
        BullsEyeImplementation bullsEyeImplementation = new BullsEyeImplementation();
        do {
            bullsEyeImplementation.randomNumberCreation();
//            System.out.println(Arrays.toString(bullsEyeImplementation.getRandomNumber()));
            startToGuess(bullsEyeImplementation);
        } while (confirmationMessagesAlert.getAlert(PLAY_THE_GAME_AGAIN));
    }

    /**
     * This user is starting to play the game and trying to guess the number
     *
     * @param bullsEyeImplementation - contains all the logic of the game we need - the random number,history of guess
     *                               counter for the total number of guesses and validation.
     */
    private void startToGuess(BullsEyeImplementation bullsEyeImplementation) {
        TextInputDialog dialog = new TextInputDialog();
        int bullsCounter = 0;
        while (bullsCounter != NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            dialog.setHeaderText("Please enter your guess");
            Optional<String> userGuessedNumber = dialog.showAndWait();
            if (userGuessedNumber.isPresent()) {
                bullsEyeImplementation.incrementByOneGuessUserTook();
                if (bullsEyeImplementation.validateResult(userGuessedNumber.get())) {
                    bullsCounter = howManyGuessedNumberRight(bullsEyeImplementation, userGuessedNumber.get());
                }
            } else if (confirmationMessagesAlert.getAlert(EXIT_THE_ROUND)) {
                break;
            }
        }
        if (bullsCounter == NUMBER_OF_DIGITS_IN_THE_NUMBER) {
            informationMessagesAlert.getAlert(InformationCode.SUMMERY, new Messages.MessagesBuilder()
                    .setTitle("Good Job!")
                    .setHeader("You successfully guessed the number")
                    .setContent("The total number of  guess it took you is " + bullsEyeImplementation.getNumberOfGuesses())
                    .build());
        }
        bullsEyeImplementation.restartTheGame();
    }

    /**
     * This function in analyzing the user guesses, to represent him the userGuessedNumber accordingly
     *
     * @param bullsEyeImplementation - the main logic of the game
     * @param userGuessedNumber      - user input
     * @return how many good guesses.
     */
    private int howManyGuessedNumberRight(BullsEyeImplementation bullsEyeImplementation, String userGuessedNumber) {
        int bullsCounter;
        int histCounter;
        String[] split = userGuessedNumber.split("", NUMBER_OF_DIGITS_IN_THE_NUMBER);
        bullsCounter = bullsEyeImplementation.findNumberOfBulls(bullsEyeImplementation.getRandomNumber(), split);
        histCounter = bullsEyeImplementation.findNumberOfHits(bullsEyeImplementation.getRandomNumber(), split);
        showHowManyBullsAndHits(bullsEyeImplementation, userGuessedNumber, bullsCounter, histCounter);
        return bullsCounter;
    }

    /**
     * @param bullsEyeImplementation - main logic of the game.
     * @param userGuessedNumber      - the number the user guessed.
     * @param bullsCounter           - number of bulls the user have.
     * @param histCounter            - number of hits the user have.
     */
    private void showHowManyBullsAndHits(BullsEyeImplementation bullsEyeImplementation,
                                         String userGuessedNumber, int bullsCounter, int histCounter) {
        String resultOfTheGuess = String.format("You guess is: " + userGuessedNumber + " You got %s bulls and %s hits ",
                bullsCounter, histCounter);
        bullsEyeImplementation.getHistoryOfGuesses().add(resultOfTheGuess);
        informationMessagesAlert.getAlert(InformationCode.YOUR_RESULT, new Messages.MessagesBuilder()
                .setHeader("Your Result")
                .setHeader(resultOfTheGuess)
                .setContent(bullsEyeImplementation.printListOfGuesses()).build());
    }
}