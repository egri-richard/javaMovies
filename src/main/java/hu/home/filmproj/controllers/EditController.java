package hu.home.filmproj.controllers;

import hu.home.filmproj.Controller;
import hu.home.filmproj.Movie;
import hu.home.filmproj.MovieDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditController extends Controller {
    @FXML
    private TextField tfTitle;
    @FXML
    private ChoiceBox<Integer> cbRatings;
    @FXML
    private Spinner<Integer> spLength;
    @FXML
    private TextField tfCategory;
    @FXML
    private Button confirmAddBtn;
    private Movie toEdit;

    @FXML
    public void confirmEditBtnClick(ActionEvent actionEvent) {
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

        toEdit.setTitle(title);
        toEdit.setCategory(category);
        toEdit.setLength(length);
        toEdit.setRating(rating);

        try {
            if (new MovieDb().editMovie(toEdit) == 1) {
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch(SQLException e) {
            errorAlert(e);
        }
    }

    public Movie getToEdit() {
        return toEdit;
    }

    public void setToEdit(Movie toEdit) {
        this.toEdit = toEdit;
        setInitials();
    }

    private void setInitials() {
        tfTitle.setText(toEdit.getTitle());
        tfCategory.setText(toEdit.getCategory());
        spLength.getValueFactory().setValue(toEdit.getLength());
        cbRatings.setValue(toEdit.getLength());
    }
}
