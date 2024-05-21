package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class FilmRetrieveServlet
 * This servlet retrieves details of a film from the database based on its ID.
 * GET request retrieves the details of the film specified by the ID from the database using FilmDAO,
 * and forwards the details to film-details.jsp for display
 */
@WebServlet("/FilmRetrieveServlet")
public class FilmRetrieveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve the film ID from the request parameter
        int filmId = Integer.parseInt(request.getParameter("id"));
        
        // Call the DAO to retrieve the film by its ID
        FilmDAO dao = new FilmDAO();
        Film film = dao.getFilmByID(filmId);
        
        // Forward the request to the JSP page for display
        RequestDispatcher dispatcher = request.getRequestDispatcher("film-details.jsp");
        
        // Pass the film object to the JSP page
        request.setAttribute("film", film);
        dispatcher.forward(request, response);
        
    }
}


