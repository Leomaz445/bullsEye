package com.game.bullseye.util.alert;

import com.game.bullseye.enums.ConfirmationCode;
import com.game.bullseye.util.Messages;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.game.bullseye.util.GameMessages.*;

public class ConfirmationMessagesAlert {

    private static final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    private final Map<ConfirmationCode, Supplier<Boolean>> mapOfConfirmation = Map.of(
            ConfirmationCode.PLAY_THE_GAME, () -> createAlert(DO_YOU_WANT_TO_PLAY_THE_GAME),
            ConfirmationCode.PLAY_THE_GAME_AGAIN, () -> createAlert(PLAY_AGAIN),
            ConfirmationCode.EXIT_THE_GAME, () -> createAlert(CONFIRM_EXIT)
    );

    public Boolean getAlert(ConfirmationCode confirmationCode) {
        return mapOfConfirmation.get(confirmationCode).get();
    }


    public boolean createAlert(Messages messages) {
        alertConfirmation.setTitle(messages.getTitle());
        alertConfirmation.setHeaderText(messages.getHeader());
        alertConfirmation.setContentText(messages.getContent());
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        if (option.get() == ButtonType.OK)
            return true;

        return false;
    }


}
