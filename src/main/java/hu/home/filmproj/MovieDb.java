package hu.home.filmproj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDb {
    Connection conn;

    //create databse connection
    public MovieDb() throws SQLException {
        //syntax: jdbc:mysql://{hostname}:{port}/{database name}
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmdb", "root", "");
    }

    //function for returning database rows stored as objects in a list
    public List<Movie> getMovies() throws SQLException {
        String sql = "SELECT * FROM filmek";
        List<Movie> retList = new ArrayList<>();

        //define statement to execute sql
        Statement stmt = conn.createStatement();
        //executed sql returns a ResultSet
        ResultSet rs = stmt.executeQuery(sql);

        //loop through returned ResultSet and add it to list
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String category = rs.getString("category");
            int length = rs.getInt("length");
            int rating = rs.getInt("rating");

            retList.add(new Movie(id, title, category, length, rating));
        }

        return retList;
    }

    //add new row in database
    public int addMovie(String title, String category, int length, int rating) throws SQLException {
        //when using PreparedStatement instead of Statement the executed sql can accept parameters
        //the parameters are "?"
        String sql = "INSERT INTO filmek(title, category, length, rating) VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        //using stmt.set{whatever} we can input the parameters
        stmt.setString(1, title);
        stmt.setString(2, category);
        stmt.setInt(3, length);
        stmt.setInt(4, rating);

        //to execute PreparedStatement we need to use executeUpdate()
        //PreparedStatement returns an integer
        //either the number of affected rows or 0 if nothing is returned
        return stmt.executeUpdate();
    }

    public int deleteMovie(int id) throws SQLException {
        String sql = "DELETE FROM filmek WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }

    public int editMovie(Movie toEdit) throws SQLException {
        String sql = "UPDATE filmek SET " +
                "title = ?, " +
                "category = ?, " +
                "length = ?, " +
                "rating = ? " +
                "WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, toEdit.getTitle());
        stmt.setString(2, toEdit.getCategory());
        stmt.setInt(3, toEdit.getLength());
        stmt.setInt(4, toEdit.getRating());
        stmt.setInt(5, toEdit.getId());
        return stmt.executeUpdate();
    }
}
