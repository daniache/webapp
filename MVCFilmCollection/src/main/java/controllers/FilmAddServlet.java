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
 * Servlet implementation class FilmAddServlet
 * This servlet provides functionality to add a new film to the database.
 * GET request displays a form for adding a new film (film-add.jsp).
 * POST request processes the form data, validates input, adds the new film to the database using FilmDAO,
 * and redirects to FilmListServlet to display the updated list of films.
 */
@WebServlet("/FilmAddServlet")
public class FilmAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Display a form for adding a new film
	    request.getRequestDispatcher("film-add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Retrieve form data for the new film
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
            int year = Integer.parseInt(request.getParameter("year"));
            String director = request.getParameter("director");
            String stars = request.getParameter("stars");
            String review = request.getParameter("review");
            
            Film newFilm = new Film(id,title,year, director, stars, review);
            
            FilmDAO dao = new FilmDAO();
			dao.addFilm(newFilm);
			
        } catch (NumberFormatException e) {
        	
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
		}
		// Redirect to FilmListServlet to display the updated list of films
		response.sendRedirect("./FilmListServlet");
	}

}
