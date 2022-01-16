package hu.home.filmproj;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    protected Stage stage;

    public Stage getStage() {
        return stage;
    }

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

    protected void alertWait(String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(msg);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }

    public static Controller newWindow(String view, String alertTitle) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle(alertTitle);
        stage.setScene(scene);
        Controller controller = fxmlLoader.getController();
        controller.stage = stage;
        return controller;
    }
}
