package com.game.bullseye.util.alert;

import com.game.bullseye.enums.InformationCode;
import com.game.bullseye.util.Messages;
import javafx.scene.control.Alert;

import java.util.Map;
import java.util.function.Consumer;

public class InformationMessagesAlert {

    private final static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private final Map<InformationCode, Consumer<Messages>> mapOfInformation = Map.of(
            InformationCode.SUMMERY, this::createAlert,
            InformationCode.YOUR_RESULT, this::createAlert
    );

    public void getAlert(InformationCode alertCode,Messages messages) {
        mapOfInformation.get(alertCode).accept(messages);
    }

    public void createAlert(Messages messages) {
        alertInformation.setTitle(messages.getTitle());
        alertInformation.setHeaderText(messages.getHeader());
        alertInformation.setContentText(messages.getContent());
        alertInformation.showAndWait();
    }
}
