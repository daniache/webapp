package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.FilmDAO;

/**
 * Servlet implementation class FilmDeleteServlet
 */
@WebServlet("/FilmDeleteServlet")
public class FilmDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int filmId =Integer.parseInt(request.getParameter("id"));
			FilmDAO dao = new FilmDAO();

			dao.deleteFilm(filmId);
			response.sendRedirect("./FilmListServlet");
			
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
		}
			
	}

}


