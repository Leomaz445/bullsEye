package com.game.bullseye.util;

import javafx.scene.control.Alert;

import java.util.Map;
import java.util.function.Supplier;

import static com.game.bullseye.util.GameMessages.*;

public class ErrorMessageConstants {

    private static final Alert alertError = new Alert(Alert.AlertType.ERROR);
    private final Map<ErrorCode, Supplier<Boolean>> mapOfErrors = Map.of(
            ErrorCode.TO_FEW_DIGITS_IN_THE_NUMBER, () -> createAlert(NEED_MORE_DIGITS),
            ErrorCode.TO_MANY_DIGITS_INT_THE_NUMBER, () -> createAlert(NEED_LESS_DIGITS),
            ErrorCode.NOT_A_NUMBER_IN_THE_INPUT, () -> createAlert(USE_ONLY_NUMBERS),
            ErrorCode.DUPLICATED_NUMBER_IN_THE_INPUT, () -> createAlert(ORIGINAL_NUMBERS)
    );

    public Boolean createErrorOutPut(ErrorCode errorCode) {
        return mapOfErrors.get(errorCode).get();
    }

    public static boolean createAlert(Messages messages) {
        alertError.setTitle(messages.getTitle());
        alertError.setHeaderText(messages.getHeader());
        alertError.setContentText(messages.getContent());
        alertError.showAndWait();
        return false;
    }
}
