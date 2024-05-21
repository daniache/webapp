package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;

/**
 * Servlet implementation class FilmListServlet
 * This servlet retrieves a list of films from the database using FilmDAO
 * and forwards the list to the film-list.jsp page for display.
 */
@WebServlet("/FilmListServlet")
public class FilmListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO dao = new FilmDAO();
		
		// Retrieve a list of films from the database
		ArrayList<Film> listFilms = dao.getAllFilms();	
		
		// Forward the list of films to the film-list.jsp page for display
		RequestDispatcher rd = request.getRequestDispatcher("film-list.jsp");
		request.setAttribute("films",listFilms);
		rd.forward(request, response);
		
	}


}
