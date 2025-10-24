package service;

import model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import repository.ICategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category();
        category.setIdCategory(1L);
        category.setName("Action");
        category.setDescription("Action movies category");
    }

    // --- Create ---
    @Test
    @DisplayName("Création d’une catégorie - succès")
    void testSaveCategory_Success() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.saveCategory(category);

        assertNotNull(result);
        assertEquals("Action", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    @DisplayName("Création d’une catégorie - échec (nom vide)")
    void testSaveCategory_InvalidName() {
        category.setName(" ");

        assertThrows(IllegalArgumentException.class,
                () -> categoryService.saveCategory(category));
        verify(categoryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Création d’une catégorie - échec (description vide)")
    void testSaveCategory_InvalidDescription() {
        category.setDescription("");

        assertThrows(IllegalArgumentException.class,
                () -> categoryService.saveCategory(category));
        verify(categoryRepository, never()).save(any());
    }

    // --- Update ---
    @Test
    @DisplayName("Mise à jour d’une catégorie - succès")
    void testUpdateCategory_Success() {
        Category updated = new Category();
        updated.setName("Thriller");
        updated.setDescription("Intense thriller category");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(updated);

        Category result = categoryService.updateCategory(1L, updated);

        assertNotNull(result);
        assertEquals("Thriller", result.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    @DisplayName("Mise à jour d’une catégorie - échec (ID inexistant)")
    void testUpdateCategory_NotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        Category updated = new Category();
        updated.setName("Comedy");
        updated.setDescription("Funny movies");

        Category result = categoryService.updateCategory(99L, updated);

        assertNull(result);
        verify(categoryRepository, never()).save(any());
    }

    // --- Read ---
    @Test
    @DisplayName("Récupération d’une catégorie par ID - succès")
    void testGetCategoryById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1L);

        assertNotNull(result);
        assertEquals("Action", result.getName());
    }

    @Test
    @DisplayName("Récupération d’une catégorie par ID - échec (non trouvée)")
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Category result = categoryService.getCategoryById(1L);

        assertNull(result);
    }

    @Test
    @DisplayName("Récupération de toutes les catégories")
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<Category> result = categoryService.getAllCategories();

        assertEquals(1, result.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Recherche par nom")
    void testGetByName() {
        when(categoryRepository.findByName("Action")).thenReturn(category);

        Category result = categoryService.getByName("Action");

        assertEquals("Action", result.getName());
        verify(categoryRepository).findByName("Action");
    }

    @Test
    @DisplayName("Recherche par mot-clé dans la description")
    void testSearchByKeyword() {
        when(categoryRepository.searchByDescription("movies"))
                .thenReturn(List.of(category));

        List<Category> result = categoryService.searchByKeyword("movies");

        assertEquals(1, result.size());
        verify(categoryRepository).searchByDescription("movies");
    }

    // --- Delete ---
    @Test
    @DisplayName("Suppression d’une catégorie - succès")
    void testDeleteCategory_Success() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Suppression d’une catégorie - échec (contrainte d’intégrité)")
    void testDeleteCategory_ConstraintViolation() {
        doThrow(new RuntimeException("Contrainte d’intégrité"))
                .when(categoryRepository).deleteById(1L);

        assertThrows(RuntimeException.class, () -> categoryService.deleteCategory(1L));
        verify(categoryRepository).deleteById(1L);
    }
}
