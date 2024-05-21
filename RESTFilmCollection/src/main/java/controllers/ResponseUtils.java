package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import models.Film;
import models.FilmList;

public class ResponseUtils {

	public static void handleJsonResponse(HttpServletResponse response, Film film) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(film);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
		
	}

	public static void handleXmlResponse(HttpServletResponse response, Film film) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(film.getClass());
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(film, response.getWriter());
		
	}

	public static void handlePlainTextResponse(HttpServletResponse response, Film film) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("ID: " + film.getId());
        out.println("Title: " + film.getTitle());
        out.println("Year: " + film.getYear());
        out.println("Director: " + film.getDirector());
        out.println("Stars: " + film.getStars());
        out.println("Review: " + film.getReview());
        out.flush();
	}

	public static void handleJsonResponse(HttpServletResponse response, ArrayList<Film> listFilms) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(listFilms);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
	}

	public static void handleXmlResponse(HttpServletResponse response, ArrayList<Film> listFilms) throws JAXBException, IOException {
		response.setContentType("application/xml");
        PrintWriter out = response.getWriter();
        FilmList filmList = new FilmList();
        filmList.setFilms(listFilms);
        JAXBContext jaxbContext = JAXBContext.newInstance(FilmList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(filmList, out);
	}

	public static void handlePlainTextResponse(HttpServletResponse response, ArrayList<Film> listFilms) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        for (Film film : listFilms) {
            out.println("ID: " + film.getId());
            out.println("Title: " + film.getTitle());
            out.println("Year: " + film.getYear());
            out.println("Director: " + film.getDirector());
            out.println("Stars: " + film.getStars());
            out.println("Review: " + film.getReview());
            out.println();
        }
        out.flush();
	}

}
