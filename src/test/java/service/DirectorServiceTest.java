package service;

import model.Director;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import repository.IDirectorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectorServiceTest {

    @Mock
    private IDirectorRepository directorRepository;

    @InjectMocks
    private DirectorService directorService;

    private Director director;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        director = new Director();
        director.setIdDirector(1L);
        director.setFirstname("Christopher");
        director.setLastname("Nolan");
        director.setNationality("British-American");
        director.setBirthDate(LocalDate.of(1970, 7, 30));
        director.setBiography("Famous for mind-bending films like Inception and Interstellar.");
    }

    // --- Create ---
    @Test
    @DisplayName("Création d’un réalisateur - succès")
    void testSaveDirector_Success() {
        when(directorRepository.save(any(Director.class))).thenReturn(director);

        Director result = directorService.saveDirector(director);

        assertNotNull(result);
        assertEquals("Christopher", result.getFirstname());
        verify(directorRepository, times(1)).save(director);
    }

    // --- Read by ID ---
    @Test
    @DisplayName("Récupération d’un réalisateur par ID - succès")
    void testGetById_Success() {
        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));

        Director result = directorService.getById(1L);

        assertNotNull(result);
        assertEquals("Nolan", result.getLastname());
    }

    @Test
    @DisplayName("Récupération d’un réalisateur par ID - échec (non trouvé)")
    void testGetById_NotFound() {
        when(directorRepository.findById(99L)).thenReturn(Optional.empty());

        Director result = directorService.getById(99L);

        assertNull(result);
    }

    // --- Read all ---
    @Test
    @DisplayName("Récupération de tous les réalisateurs")
    void testFindAll() {
        when(directorRepository.findAll()).thenReturn(List.of(director));

        List<Director> result = directorService.findAll();

        assertEquals(1, result.size());
        assertEquals("Christopher", result.get(0).getFirstname());
        verify(directorRepository, times(1)).findAll();
    }

    // --- Search by name ---
    @Test
    @DisplayName("Recherche d’un réalisateur par prénom")
    void testGetByName() {
        when(directorRepository.findByFirstname("Christopher")).thenReturn(director);

        Director result = directorService.getByName("Christopher");

        assertEquals("Christopher", result.getFirstname());
        verify(directorRepository).findByFirstname("Christopher");
    }

    // --- Update ---
    @Test
    @DisplayName("Mise à jour d’un réalisateur - succès")
    void testUpdateDirector_Success() {
        Director updated = new Director();
        updated.setFirstname("Chris");
        updated.setLastname("Nolan");
        updated.setNationality("British-American");
        updated.setBirthDate(LocalDate.of(1970, 7, 30));
        updated.setBiography("Updated biography");

        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        when(directorRepository.save(any(Director.class))).thenReturn(updated);

        Director result = directorService.updateDirector(1L, updated);

        assertNotNull(result);
        assertEquals("Chris", result.getFirstname());
        assertEquals("Updated biography", result.getBiography());
        verify(directorRepository, times(1)).save(any(Director.class));
    }

    @Test
    @DisplayName("Mise à jour d’un réalisateur - échec (non trouvé)")
    void testUpdateDirector_NotFound() {
        when(directorRepository.findById(99L)).thenReturn(Optional.empty());

        Director updated = new Director();
        updated.setFirstname("Steven");

        Director result = directorService.updateDirector(99L, updated);

        assertNull(result);
        verify(directorRepository, never()).save(any());
    }

    // --- Delete ---
    @Test
    @DisplayName("Suppression d’un réalisateur - succès")
    void testDeleteDirector_Success() {
        doNothing().when(directorRepository).deleteById(1L);

        directorService.deleteDirector(1L);

        verify(directorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Suppression d’un réalisateur - échec (contrainte d’intégrité)")
    void testDeleteDirector_ConstraintViolation() {
        doThrow(new RuntimeException("Contrainte d’intégrité"))
                .when(directorRepository).deleteById(1L);

        assertThrows(RuntimeException.class, () -> directorService.deleteDirector(1L));
        verify(directorRepository).deleteById(1L);
    }
}
