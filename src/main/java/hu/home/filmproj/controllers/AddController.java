package hu.home.filmproj.controllers;

import hu.home.filmproj.Controller;
import hu.home.filmproj.MovieDb;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.SQLException;

public class AddController extends Controller {
    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private ChoiceBox<Integer> cbRatings;
    @javafx.fxml.FXML
    private Spinner<Integer> spLength;
    @javafx.fxml.FXML
    private TextField tfCategory;
    @javafx.fxml.FXML
    private Button confirmAddBtn;

    @javafx.fxml.FXML
    public void confirmAddBtnClick(ActionEvent actionEvent) {
        //simple validation
        String title = tfTitle.getText().trim();
        if (title.isEmpty()) {
            alert("A Cím mező nem lehet üres");
            return;
        }

        String category = tfCategory.getText().trim();
        if (category.isEmpty()) {
            alert("A Kategória mező nem lehet üres");
            return;
        }

        int length = 0;
        try { length = spLength.getValue(); }
        catch(NullPointerException e) {
            errorAlert(e);
            alert("A Hossz megadása kötelező");
            return;
        } catch(Exception e) {
            errorAlert(e);
            alert("A Hossz csak 1 és 999 közötti szám lehet");
            return;
        }

        if (cbRatings.getSelectionModel().getSelectedIndex() == -1) {
            alert("Az értékelés kiválasztása kötelező");
            return;
        }
        int rating = cbRatings.getValue();

        try {
            if (new MovieDb().addMovie(title, category, length, rating) == 1) {
                alert("A film hozzáadása sikeres");
            } else {
                alert("A film hozzáadása sikertelen");
            }
        } catch (SQLException e) {
            errorAlert(e);
        }
    }
}
