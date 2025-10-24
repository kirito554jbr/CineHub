package controller;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PostMapping("/update/{id}")
    public Category update(@PathVariable("id") Long id, @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(id, updatedCategory);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully!";
    }

    @GetMapping("/get/{id}")
    public Category getById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/all")
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/search")
    public List<Category> search(@RequestParam("keyword") String keyword) {
        return categoryService.searchByKeyword(keyword);
    }

    @GetMapping("/byName")
    public Category getByName(@RequestParam("name") String name) {
        return categoryService.getByName(name);
    }
}
