package hu.home.filmproj;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public abstract class HelperFunctions {
    protected void errorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getClass().toString());
        alert.setContentText(e.getMessage());
        alert.show();
    }

    protected void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(msg);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.show();
    }
}
