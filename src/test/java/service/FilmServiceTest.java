package service;

import model.Category;
import model.Director;
import model.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import repository.IFilmRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private IFilmRepository filmRepository;

    @Mock
    private DirectorService directorService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private FilmService filmService;

    private Film film;
    private Director director;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        director = new Director();
        director.setIdDirector(1L);
        director.setFirstname("Christopher");
        director.setLastname("Nolan");

        category = new Category();
        category.setIdCategory(1L);
        category.setName("Science Fiction");

        film = new Film();
        film.setIdFilm(1L);
        film.setTitle("Inception");
        film.setReleaseYear(2010);
        film.setDuration(148);
        film.setRating(8.8);
        film.setDirector(director);
        film.setCategory(category);
    }

    // --- Test création ---
    @Test
    @DisplayName("Création d’un film - succès")
    void testSaveFilm_Success() {
        when(filmRepository.save(any(Film.class))).thenReturn(film);

        Film result = filmService.saveFilm(film);

        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
        verify(filmRepository, times(1)).save(film);
    }

    // --- Test récupération par ID ---
    @Test
    @DisplayName("Récupération d’un film par ID - succès")
    void testGetFilmById_Success() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        Film result = filmService.getFilmById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdFilm());
    }

    @Test
    @DisplayName("Récupération d’un film par ID - échec (film non trouvé)")
    void testGetFilmById_Failure() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        Film result = filmService.getFilmById(1L);

        assertNull(result);
    }

    // --- Test mise à jour ---
    @Test
    @DisplayName("Mise à jour d’un film - succès")
    void testUpdateFilm_Success() {
        Film updated = new Film();
        updated.setTitle("Inception 2");
        updated.setReleaseYear(2025);
        updated.setDuration(160);
        updated.setRating(9.0);
        updated.setDirector(director);
        updated.setCategory(category);

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(directorService.getById(anyLong())).thenReturn(director);
        when(categoryService.getCategoryById(anyLong())).thenReturn(category);
        when(filmRepository.save(any(Film.class))).thenReturn(updated);

        Film result = filmService.updateFilm(1L, updated);

        assertNotNull(result);
        assertEquals("Inception 2", result.getTitle());
        verify(filmRepository, times(1)).save(any(Film.class));
    }

    @Test
    @DisplayName("Mise à jour d’un film - échec (film non trouvé)")
    void testUpdateFilm_Failure() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        Film result = filmService.updateFilm(1L, film);

        assertNull(result);
    }

    // --- Test suppression ---
    @Test
    @DisplayName("Suppression d’un film - succès")
    void testDeleteFilm_Success() {
        doNothing().when(filmRepository).deleteById(1L);

        filmService.deleteFilm(1L);

        verify(filmRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Suppression d’un film - échec (contrainte d’intégrité)")
    void testDeleteFilm_WithConstraint() {
        doThrow(new RuntimeException("Contrainte d’intégrité"))
                .when(filmRepository).deleteById(1L);

        assertThrows(RuntimeException.class, () -> filmService.deleteFilm(1L));
        verify(filmRepository, times(1)).deleteById(1L);
    }

    // --- Test recherche par titre ---
    @Test
    @DisplayName("Recherche d’un film par titre")
    void testGetFilmByTitle() {
        when(filmRepository.getFilmByTitle("Inception")).thenReturn(film);

        Film result = filmService.getFilmByTitle("Inception");

        assertEquals("Inception", result.getTitle());
        verify(filmRepository).getFilmByTitle("Inception");
    }

    // --- Test recherche par catégorie ---
    @Test
    @DisplayName("Recherche de films par catégorie")
    void testGetFilmsByCategory() {
        when(filmRepository.findByCategoryName("Science Fiction"))
                .thenReturn(List.of(film));

        List<Film> result = filmService.getFilmsByCategory("Science Fiction");

        assertEquals(1, result.size());
        verify(filmRepository).findByCategoryName("Science Fiction");
    }

    // --- Test recherche par réalisateur ---
    @Test
    @DisplayName("Recherche de films par réalisateur")
    void testGetFilmsByDirector() {
        when(filmRepository.findAll()).thenReturn(List.of(film));

        List<Film> result = filmService.getFilmsByDirector("Nolan");

        assertEquals(1, result.size());
        assertEquals("Nolan", result.get(0).getDirector().getLastname());
    }

    // --- Test recherche par année ---
    @Test
    @DisplayName("Recherche de films par année de sortie")
    void testGetFilmByReleaseYear() {
        when(filmRepository.findAll()).thenReturn(List.of(film));

        List<Film> result = filmService.getFilmByReleaseYear(2010);

        assertEquals(1, result.size());
        assertEquals(2010, result.get(0).getReleaseYear());
    }

    // --- Test recherche par note ---
    @Test
    @DisplayName("Recherche de films par note exacte")
    void testGetFilmsByRating() {
        when(filmRepository.findAll()).thenReturn(List.of(film));

        List<Film> result = filmService.getFilmsByRating(8.8);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Recherche de films par note minimale")
    void testGetFilmsByMinimumRating() {
        when(filmRepository.findAll()).thenReturn(List.of(film));

        List<Film> result = filmService.getFilmsByMinumumRating(8.0);

        assertEquals(1, result.size());
    }
}
