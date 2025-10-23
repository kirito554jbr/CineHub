package service;

import model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IFilmRepository;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    IFilmRepository filmRepository;

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public Film updateFilm(Film updatedFilm) {
        return filmRepository.findById(updatedFilm.getIdFilm()).map(film -> {
            film.setTitle(updatedFilm.getTitle());
            film.setReleaseYear(updatedFilm.getReleaseYear());
            film.setDuration(updatedFilm.getDuration());
            film.setSynopsis(updatedFilm.getSynopsis());
            film.setDirector(updatedFilm.getDirector());
            film.setCategory(updatedFilm.getCategory());
            return filmRepository.save(film);
        }).orElse(null);
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }


    public List<Film> getFilms() {
        return filmRepository.findAll();
    }
}
