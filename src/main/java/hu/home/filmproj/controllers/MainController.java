package hu.home.filmproj.controllers;

import hu.home.filmproj.Controller;
import hu.home.filmproj.MainApplication;
import hu.home.filmproj.Movie;
import hu.home.filmproj.MovieDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainController extends Controller {

    @FXML
    private Button editBtn;
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, Integer> colRating;
    @FXML
    private TableColumn<Movie, Integer> colLength;
    @FXML
    private TableColumn<Movie, String> colCategory;
    @FXML
    private TableColumn<Movie, String> colTitle;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    private MovieDb db;

    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        try { fillTable(); } catch (SQLException e) {
            errorAlert(e);
        }
    }

    @FXML
    public void editBtnClick(ActionEvent actionEvent) {
        if (movieTable.getSelectionModel().getSelectedIndex() == -1) {
            alert("Módosításhoz kérem vállaszon ki elöbb egy elemet");
            return;
        }

        Movie Selected = movieTable.getSelectionModel().getSelectedItem();

        try {
            EditController editWindow = (EditController) newWindow("edit-view.fxml", "Film módosítása");

            editWindow.setToEdit(Selected);

            editWindow.getStage().setOnHiding(event -> movieTable.refresh());
            editWindow.getStage().show();
        } catch (Exception e) {
            errorAlert(e);
        }
    }

    @FXML
    public void addBtnClick(ActionEvent actionEvent) {
        try {
            Controller addWindow = newWindow("add-view.fxml", "Film hozzáadása");
            addWindow.getStage().setOnCloseRequest(event -> {
                try { fillTable(); } catch (SQLException e) {
                    errorAlert(e);
                }
            });
            addWindow.getStage().show();
        } catch (Exception e) {
            errorAlert(e);
        }

    }

    @FXML
    public void deleteBtnClick(ActionEvent actionEvent) {
        if (movieTable.getSelectionModel().getSelectedIndex() == -1) {
            alert("Törléshez kérem vállaszon ki elöbb egy elemet");
            return;
        }

        Movie Selected = movieTable.getSelectionModel().getSelectedItem();

        try {
            if (db.deleteMovie(Selected.getId()) == 1) {
                alert("Sikeres törlés");
            } else {
                alert("Sikertelen törlés");
            }

            fillTable();
        } catch (SQLException e) {
            errorAlert(e);
        }
    }

    private void fillTable() throws SQLException {
        db = new MovieDb();
        List<Movie> movies = db.getMovies();

        movieTable.getItems().clear();

        for (Movie m: movies) {
            movieTable.getItems().add(m);
        }
    }
}