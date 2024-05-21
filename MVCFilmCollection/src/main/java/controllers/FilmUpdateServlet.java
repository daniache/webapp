package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class FilmUpdateServlet
 * This servlet provides functionality to update an existing film in the database.
 * GET request retrieves the details of the film to be updated based on its ID,
 * and forwards the details to film-update.jsp for editing.
 * POST request processes the form data submitted for updating the film,
 * validates input, updates the film in the database using FilmDAO,
 * and redirects to FilmListServlet to display the updated list of films.
 */
@WebServlet("/FilmUpdateServlet")
public class FilmUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int filmId = Integer.parseInt(request.getParameter("id"));
        
        // Call the DAO to retrieve the film by its ID
        FilmDAO dao = new FilmDAO();
        Film film = dao.getFilmByID(filmId);
		
        // Forward the film details to film-update.jsp for editing
        request.setAttribute("film", film);
		request.getRequestDispatcher("film-update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get film details from the submitted form
        int filmId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        String director = request.getParameter("director");
        String stars = request.getParameter("stars");
        String review = request.getParameter("review");
        
        Film updatedFilm = new Film(filmId, title, year,director,stars,review);
        
        try {
        FilmDAO dao = new FilmDAO();
		dao.updateFilm(filmId, updatedFilm);
		
		response.sendRedirect("./FilmListServlet");
	} catch (NumberFormatException e) {
        // Handle invalid input (e.g., show an error page) 
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
    }
   }
}
