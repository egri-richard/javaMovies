package hu.home.filmproj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDb {
    Connection conn;

    public MovieDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmdb", "root", "");
    }

    public List<Movie> getMovies() throws SQLException {
        String sql = "SELECT * FROM filmek";
        List<Movie> retList = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String category = rs.getString("category");
            int length = rs.getInt("length");
            int rating = rs.getInt("rating");

            retList.add(new Movie(id, title, category, length, rating));
        }

        return retList;
    }

    public int addMovie(String title, String category, int length, int rating) throws SQLException {
        String sql = "INSERT INTO filmek(title, category, length, rating) VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, title);
        stmt.setString(2, category);
        stmt.setInt(3, length);
        stmt.setInt(4, rating);
        return stmt.executeUpdate();
    }
}
