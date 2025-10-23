package controller;

import model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping("/save")
    @ResponseBody
    public String saveFilm(@RequestParam String title,
                          @RequestParam String genre,
                          @RequestParam int year) {
//        Film film = new Film(title, genre, year);
//        filmService.saveFilm(film);
        return "Film saved successfully!";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public java.util.List<Film> getAllFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "FilmController is working!";
    }

}
