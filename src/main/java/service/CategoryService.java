package service;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;


    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category saveCategory(Category category) {
        validateCategory(category);
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            return categoryRepository.save(category);
        }).orElse(null);
    }

    @Transactional
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional
    public List<Category> searchByKeyword(String keyword) {
        return categoryRepository.searchByDescription(keyword);
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Category description cannot be empty");
        }

        if (category.getName().length() > 100) {
            throw new IllegalArgumentException("Category name must be under 100 characters");
        }

        if (category.getDescription().length() > 2000) {
            throw new IllegalArgumentException("Description too long (max 2000 characters)");
        }
    }
}
