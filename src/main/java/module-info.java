module com.game.bullseye {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.bullseye to javafx.fxml;
    exports com.game.bullseye;
    exports com.game.bullseye.enums;
    opens com.game.bullseye.enums to javafx.fxml;
}