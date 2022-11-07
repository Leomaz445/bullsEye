package com.game.bullseye.util.alert;

import com.game.bullseye.enums.ErrorCode;
import com.game.bullseye.util.Messages;
import javafx.scene.control.Alert;

import java.util.Map;
import java.util.function.Supplier;

import static com.game.bullseye.util.GameMessages.*;

/**
 * This Class contains all the error messages we present to the user
 */
public class ErrorMessagesAlert {

    private static final Alert alertError = new Alert(Alert.AlertType.ERROR);
    private final Map<ErrorCode, Supplier<Boolean>> mapOfErrors = Map.of(
            ErrorCode.TO_FEW_DIGITS_IN_THE_NUMBER, () -> createAlert(NEED_MORE_DIGITS),
            ErrorCode.TO_MANY_DIGITS_INT_THE_NUMBER, () -> createAlert(NEED_LESS_DIGITS),
            ErrorCode.NOT_A_NUMBER_IN_THE_INPUT, () -> createAlert(USE_ONLY_NUMBERS),
            ErrorCode.DUPLICATED_NUMBER_IN_THE_INPUT, () -> createAlert(ORIGINAL_NUMBERS)
    );
    /**
     * This function is using the hashmap to show the message to the user and then return his choice.
     *
     * @param errorCode - the code of the message we want to show to the user .
     * @return return false
     */
    public boolean getAlert(ErrorCode errorCode) {
        return mapOfErrors.get(errorCode).get();
    }

    private boolean createAlert(Messages messages) {
        alertError.setTitle(messages.getTitle());
        alertError.setHeaderText(messages.getHeader());
        alertError.setContentText(messages.getContent());
        alertError.showAndWait();
        return false;
    }
}
