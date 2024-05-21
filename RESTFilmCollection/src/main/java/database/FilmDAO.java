package database;
import java.util.ArrayList;

import models.Film;

import java.sql.*;


public class FilmDAO {
    private static FilmDAO instance = null;
    private Connection conn = null;

    public FilmDAO() {
        openConnection();
    }

    public static FilmDAO getInstance() {
        if (instance == null) {
            instance = new FilmDAO();
        }
        return instance;
    }

    private void openConnection() {
    	String user = "your_database_username";
        String password = "your_database_password";
        String url = "jdbc:mysql://localhost:3306/your_db_name?user=your_username";

        // loading jdbc driver for mysql
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // connecting to database
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ArrayList<Film> getAllFilms() {
        ArrayList<Film> allFilms = new ArrayList<>();

        // Create select prepared statement and execute it
        try {
            String selectSQL = "select * from films";
            PreparedStatement allPStmt = conn.prepareStatement(selectSQL);
            ResultSet rs = allPStmt.executeQuery();
            // Retrieve the results
            while (rs.next()) {
                Film oneFilm = getNextFilm(rs);
                allFilms.add(oneFilm);
            }

            allPStmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return allFilms;
    }

    public Film getFilmByID(int id) {
        Film oneFilm = null;

        // Create prepared select statement and execute it
        try {
            String selectSQL = "select * from films where id= ?";
            PreparedStatement getFilmPStmt = conn.prepareStatement(selectSQL);
            getFilmPStmt.setInt(1, id);
            ResultSet rs1 = getFilmPStmt.executeQuery();
            // Retrieve the results
            while (rs1.next()) {
                oneFilm = getNextFilm(rs1);
            }

            getFilmPStmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return oneFilm;
    }

    public void addFilm(Film film) {
        try {
            String insertSQL = "insert into films (id, title, year, director,stars,review) values (?,?,?,?,?,?);";
            PreparedStatement addPStmt = conn.prepareStatement(insertSQL);
            addPStmt.setInt(1, film.getId());
            addPStmt.setString(2, film.getTitle());
            addPStmt.setInt(3, film.getYear());
            addPStmt.setString(4, film.getDirector());
            addPStmt.setString(5, film.getStars());
            addPStmt.setString(6, film.getReview());

            addPStmt.executeUpdate();
            addPStmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void updateFilm(int id, Film film) {
        try {
            String updateSQL = "update films set title = ?, year = ?, director = ?, stars = ?, review = ? where id = ?; ";
            PreparedStatement updPStmt = conn.prepareStatement(updateSQL);
            updPStmt.setInt(6, film.getId());
            updPStmt.setString(1, film.getTitle());
            updPStmt.setInt(2, film.getYear());
            updPStmt.setString(3, film.getDirector());
            updPStmt.setString(4, film.getStars());
            updPStmt.setString(5, film.getReview());

            updPStmt.executeUpdate();
            updPStmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void deleteFilm(int id) {
        try {
            String deleteSQL = "DELETE FROM films WHERE id = ?;";
            PreparedStatement delPStmt = conn.prepareStatement(deleteSQL);
            delPStmt.setInt(1, id);
            delPStmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ArrayList<Film> searchFilm(String searchTerm) {
        ArrayList<Film> searchResults = new ArrayList<>();

        try {
            String searchSQL = "SELECT * FROM films where id like ? OR title like ? OR year like ? or director like ? or stars like ?; ";
            PreparedStatement searchPStmt = conn.prepareStatement(searchSQL);
            searchPStmt.setString(1, "%" + searchTerm + "%"); // Search for partial matches in ID
            searchPStmt.setString(2, "%" + searchTerm + "%"); // Search for partial matches in title
            searchPStmt.setString(3, "%" + searchTerm + "%"); // Search for partial matches in year
            searchPStmt.setString(4, "%" + searchTerm + "%"); // Search for partial matches in director
            searchPStmt.setString(5, "%" + searchTerm + "%"); // Search for partial matches in stars

            ResultSet rs2 = searchPStmt.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id");
                String title = rs2.getString("title");
                int year = rs2.getInt("year");
                String director = rs2.getString("director");
                String stars = rs2.getString("stars");
                String review = rs2.getString("review");

                Film film = new Film(id, title, year, director, stars, review);
                searchResults.add(film);
            }

            rs2.close();
            searchPStmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return searchResults;
    }

    private Film getNextFilm(ResultSet rs) throws SQLException {
        return new Film(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("year"),
                rs.getString("director"),
                rs.getString("stars"),
                rs.getString("review"));
    }

    private void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}