package hu.home.filmproj;

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

public class MainController extends HelperFunctions{

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

    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        try {
            List<Movie> movies = new MovieDb().getMovies();
            for (Movie m: movies) {
                movieTable.getItems().add(m);
            }
        } catch (SQLException e) {
            errorAlert(e);
        }
    }

    @FXML
    public void editBtnClick(ActionEvent actionEvent) {
    }

    @FXML
    public void addBtnClick(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("Movies");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            errorAlert(e);
        }

    }

    @FXML
    public void deleteBtnClick(ActionEvent actionEvent) {
    }
}