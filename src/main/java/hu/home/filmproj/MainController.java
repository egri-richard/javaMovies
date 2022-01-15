package hu.home.filmproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class MainController {

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

    private void errorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getClass().toString());
        alert.setContentText(e.getMessage());
        alert.show();
    }

    @FXML
    public void editBtnClick(ActionEvent actionEvent) {
    }

    @FXML
    public void addBtnClick(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteBtnClick(ActionEvent actionEvent) {
    }
}