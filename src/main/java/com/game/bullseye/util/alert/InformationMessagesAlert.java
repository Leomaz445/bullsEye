package com.game.bullseye.util.alert;

import com.game.bullseye.enums.InformationCode;
import com.game.bullseye.util.Messages;
import javafx.scene.control.Alert;

import java.util.Map;
import java.util.function.Consumer;
/**
 * This Class contains all the information messages we present to the user
 */
public class InformationMessagesAlert {

    private final static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    //implementing the consumer interface
    private final Map<InformationCode, Consumer<Messages>> mapOfInformation = Map.of(
            InformationCode.SUMMERY, this::createAlert,
            InformationCode.YOUR_RESULT, this::createAlert
    );

    /**
     * This function suing the hashmap to return the right message.
     * @param alertCode - the code of the summery message
     * @param messages - the message we want to show to the user
     */
    public void getAlert(InformationCode alertCode,Messages messages) {
        mapOfInformation.get(alertCode).accept(messages);
    }

    /**
     * This function is creating the showing to the user the alert message.
     * @param messages - the message that we want to represent to the user.
     */
    private void createAlert(Messages messages) {
        alertInformation.setTitle(messages.getTitle());
        alertInformation.setHeaderText(messages.getHeader());
        alertInformation.setContentText(messages.getContent());
        alertInformation.showAndWait();
    }
}
