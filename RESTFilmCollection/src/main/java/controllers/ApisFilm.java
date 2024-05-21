package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.FilmDAO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import models.Film;
import models.FilmList;

/**
 * Servlet implementation class ApisFilm
 */
@WebServlet("/ApisFilm")
public class ApisFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String format = request.getParameter("format");
        PrintWriter out = response.getWriter();

        try {
            if (request.getParameter("id") != null) {
                // Request is for retrieving a single film by ID
                int filmId = Integer.parseInt(request.getParameter("id"));
                FilmDAO dao = new FilmDAO();
                Film film = dao.getFilmByID(filmId);

                if (film != null) {
                    if ("json".equalsIgnoreCase(format)) {
                    	ResponseUtils.handleJsonResponse(response, film);
                    } else if ("xml".equalsIgnoreCase(format)) {
                    	ResponseUtils.handleXmlResponse(response, film);
                    } else {
                    	ResponseUtils.handlePlainTextResponse(response, film);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("Film not found");
                }
            } else {
                // Request is for retrieving a list of films
                FilmDAO dao = new FilmDAO();
                ArrayList<Film> listFilms = dao.getAllFilms();

                if ("json".equalsIgnoreCase(format)) {
                	ResponseUtils.handleJsonResponse(response, listFilms);
                } else if ("xml".equalsIgnoreCase(format)) {
                	ResponseUtils.handleXmlResponse(response, listFilms);
                } else {
                	ResponseUtils.handlePlainTextResponse(response, listFilms);
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Invalid input");
        } catch (JAXBException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Error converting to XML.");
        } finally {
            out.flush();
            out.close();
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String format = request.getParameter("format");

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
            int year = Integer.parseInt(request.getParameter("year"));
            String director = request.getParameter("director");
            String stars = request.getParameter("stars");
            String review = request.getParameter("review");
            
            Film newFilm = new Film(id,title,year, director, stars, review);
            
            FilmDAO dao = new FilmDAO();
			dao.addFilm(newFilm);
			
			PrintWriter out = response.getWriter();

            // Output the data based on the selected format
            if ("json".equalsIgnoreCase(format)) {
                response.setContentType("application/json");

                // Convert the newly added film to JSON
                Gson gson = new Gson();
                String json = gson.toJson(newFilm);
                out.println(json);
            } else if ("xml".equalsIgnoreCase(format)) {
                response.setContentType("application/xml");

                // Convert the newly added film to XML using JAXB
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
                    Marshaller marshaller = jaxbContext.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.marshal(newFilm, out);
                } catch (JAXBException e) {
                    e.printStackTrace();
                    out.println("Error converting to XML.");
                }
            } else {
                // Default to plain text format        	
                response.setContentType("text/plain");
                out.println("Film added successfully:");
                out.println("ID: " + newFilm.getId());
                out.println("Title: " + newFilm.getTitle());
                out.println("Year: " + newFilm.getYear());
                out.println("Director: " + newFilm.getDirector());
                out.println("Stars: " + newFilm.getStars());
                out.println("Review: " + newFilm.getReview());
            }

            out.flush();
			
        } catch (NumberFormatException e) {
        	
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
		}
		
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String contentType = request.getHeader("Content-Type");
	        String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
	        int filmId=-1;
	        Film updatedFilm = null;
	        FilmDAO dao = new FilmDAO();

	        switch (contentType) {
	            case "application/json":
	                updatedFilm = new Gson().fromJson(data, Film.class);
	                filmId = updatedFilm.getId();
	                filmId = updatedFilm.getId();
	                String titlejson = updatedFilm.getTitle();
	                int yearjson = updatedFilm.getYear();
	                String directorjson = updatedFilm.getDirector();
	                String starsjson = updatedFilm.getStars();
	                String reviewjson = updatedFilm.getReview();
	                
	                updatedFilm = new Film(filmId, titlejson, yearjson, directorjson, starsjson, reviewjson);
	               
	                dao.updateFilm(filmId, updatedFilm);
	                break;
	            case "application/xml":
	                try {
	                    updatedFilm = (Film) JAXBContext.newInstance(Film.class).createUnmarshaller().unmarshal(new StringReader(data));
	                    filmId = updatedFilm.getId();
	                    String titlexml = updatedFilm.getTitle();
	                    int yearxml = updatedFilm.getYear();
	                    String directorxml = updatedFilm.getDirector();
	                    String starsxml = updatedFilm.getStars();
	                    String reviewxml = updatedFilm.getReview();
	                    
	                    updatedFilm = new Film(filmId, titlexml, yearxml, directorxml, starsxml, reviewxml);
	                    
	                    dao.updateFilm(filmId, updatedFilm);
	                } catch (JAXBException e) {
	                    e.printStackTrace();
	                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                    return;
	                }
	                break;
	            case "text/plain":
	            	System.out.println("Received plain text data: " + data);
	            	try {
	                    String[] splitData = data.split(":");
	                    if (splitData.length != 6) {
	                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                        return;
	                    }
	                    filmId = Integer.parseInt(splitData[0].trim());
	                    String title = splitData[1].trim();
	                    int year = Integer.parseInt(splitData[2].trim());
	                    String director = splitData[3].trim();
	                    String stars = splitData[4].trim();
	                    String review = splitData[5].trim();

	                    // Create updated film object
	                    updatedFilm = new Film(filmId, title, year, director, stars, review);

	                    // Update the film
	                    dao.updateFilm(filmId, updatedFilm);


	                 // Return success response
	                    response.setStatus(HttpServletResponse.SC_OK);
	                } catch (NumberFormatException e) {
	                    e.printStackTrace();
	                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                    return;
	                }
	                break;
	            default:
	                response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
	                return;
	        }
	    }
	

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
			
			int filmId =Integer.parseInt(request.getParameter("id"));
			FilmDAO dao = new FilmDAO();

			dao.deleteFilm(filmId);
			
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
		}
			
	}

}


