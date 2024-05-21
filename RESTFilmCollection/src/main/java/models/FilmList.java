package models;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "filmList")
public class FilmList {
    private ArrayList<Film> films;
    
    public FilmList() {
    }

    public FilmList(ArrayList<Film> films) {
        this.films = films;
    }

    //@XmlElementWrapper(name = "films")
    //@XmlElement(name = "film")
    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }
  }