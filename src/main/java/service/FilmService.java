package service;

import model.Category;
import model.Director;
import model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IFilmRepository;

import java.util.List;

@Service
public class FilmService {


    IFilmRepository filmRepository;
    DirectorService directorService;
    CategoryService categoryService;

    public FilmService(IFilmRepository filmRepository, DirectorService directorService, CategoryService categoryService) {
        this.filmRepository = filmRepository;
        this.directorService = directorService;
        this.categoryService = categoryService;
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public Film updateFilm(Long id,Film updatedFilm) {
        return filmRepository.findById(id)
                .map(film -> { film.setTitle(updatedFilm.getTitle());
                    film.setReleaseYear(updatedFilm.getReleaseYear());
                    film.setDuration(updatedFilm.getDuration());
                    film.setSynopsis(updatedFilm.getSynopsis());
                    film.setRating(updatedFilm.getRating());
                    film.setDirector(directorService.getById(updatedFilm.getDirector().getIdDirector()));
                    film.setCategory(categoryService.getCategoryById(updatedFilm.getCategory().getIdCategory()));
                    return filmRepository.save(film);
                }).orElse(null);
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }


    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmByTitle(String title) {
        return filmRepository.getFilmByTitle(title);
    }

    public List<Film> getFilmsByCategory(String categoryName) {
        return filmRepository.findByCategoryName(categoryName);
    }

    public List<Film> getFilmsByDirector(String directorName) {
//        return filmRepository.getFilmsByDirector_lastname(directorName);
        return filmRepository.findAll().stream()
                .filter(film -> film.getDirector() != null &&
                        (film.getDirector().getFirstname().equalsIgnoreCase(directorName) ||
                         film.getDirector().getLastname().equalsIgnoreCase(directorName)))
                .toList();
    }

    public List<Film> getFilmByReleaseYear(int releaseYear) {
        return filmRepository.findAll().stream()
                .filter(film -> film.getReleaseYear() == releaseYear)
                .toList();
    }

    public List<Film> getFilmsByRating(Double rating) {
        return filmRepository.findAll().stream()
                .filter(film -> film.getRating() != null && film.getRating().equals(rating))
                .toList();
    }

    public List<Film> getFilmsByMinumumRating(Double minRating) {
        return filmRepository.findAll().stream()
                .filter(film -> film.getRating() != null && film.getRating() >= minRating)
                .toList();
    }
}
