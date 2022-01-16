module hu.home.filmproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hu.home.filmproj to javafx.fxml;
    exports hu.home.filmproj;
    exports hu.home.filmproj.controllers;
    opens hu.home.filmproj.controllers to javafx.fxml;
}