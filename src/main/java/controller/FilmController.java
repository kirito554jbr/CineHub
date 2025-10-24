package controller;

import model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping("/save")
    @ResponseBody
    public Film saveFilm(@RequestBody  Film film) {
        return filmService.saveFilm(film);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Film updateFilm(@PathVariable("id") Long id, @RequestBody Film updatedFilm) {
        return filmService.updateFilm(id ,updatedFilm);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilm(id);
        return "Film deleted successfully!";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Film getFilmById(@PathVariable("id") Long id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Film> getAllFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/byTitle")
    @ResponseBody
    public Film getFilmByTitle(@RequestParam("title") String title) {
        return filmService.getFilmByTitle(title);
    }

    @GetMapping("/byCategory")
    @ResponseBody
    public List<Film> getFilmsByCategory(@RequestParam("categoryName") String categoryName) {
        return filmService.getFilmsByCategory(categoryName);
    }

    @GetMapping("/byDirector")
    @ResponseBody
    public List<Film> getFilmsByDirector(@RequestParam("directorName") String directorName) {
        return filmService.getFilmsByDirector(directorName);
    }

    @GetMapping("/byReleaseYear")
    @ResponseBody
    public List<Film> getFilmByReleaseYear(@RequestParam("releaseYear") int releaseYear) {
        return filmService.getFilmByReleaseYear(releaseYear);
    }

    @GetMapping("/byRating")
    @ResponseBody
    public List<Film> getFilmsByRating(@RequestParam("rating") Double rating){
        return filmService.getFilmsByRating(rating);
    }

    @GetMapping("/byMinimumRating")
    @ResponseBody
    public List<Film> getFilmsByMinimumRating(@RequestParam("minRating") Double minRating) {
        return filmService.getFilmsByMinumumRating(minRating);
    }

}
