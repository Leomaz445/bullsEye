package com.game.bullseye.util.alert;

import com.game.bullseye.enums.ConfirmationCode;
import com.game.bullseye.util.Messages;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.game.bullseye.util.GameMessages.*;

/**
 * This Class contains all the conformation messages we present to the user
 * - Play The game - if the user wants to start to play
 * - Play The game Again - if he wants to play Again.
 * - Exit the game - if he wants to exit the game.
 */
public class ConfirmationMessagesAlert{

    private static final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    //This hashmap it's implementing Supplier interface
    private final Map<ConfirmationCode, Supplier<Boolean>> mapOfConfirmation = Map.of(
            ConfirmationCode.PLAY_THE_GAME, () -> createAlert(DO_YOU_WANT_TO_PLAY_THE_GAME),
            ConfirmationCode.PLAY_THE_GAME_AGAIN, () -> createAlert(PLAY_AGAIN),
            ConfirmationCode.EXIT_THE_GAME, () -> createAlert(CONFIRM_EXIT)
    );

    /**
     * This function is using the hashmap to show the message to the user and then return his choice.
     *
     * @param confirmationCode - the code of the message we want to show to the user .
     * @return true - if the user pressed yes else we return false.
     */
    public boolean getAlert(ConfirmationCode confirmationCode) {
        return mapOfConfirmation.get(confirmationCode).get();
    }



    /**
     * This creates the alert messages of type conformation
     *
     * @param messages - the message object we want to be shown in the alert message
     * @return true - if the user pressed ok else false.
     */
    private boolean createAlert(Messages messages) {
        alertConfirmation.setTitle(messages.getTitle());
        alertConfirmation.setHeaderText(messages.getHeader());
        alertConfirmation.setContentText(messages.getContent());
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        if (option.get() == ButtonType.OK)
            return true;

        return false;
    }


}
