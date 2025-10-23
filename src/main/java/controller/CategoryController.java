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

    @GetMapping("/search")
    public List<Category> search(@RequestParam String keyword) {
        return categoryService.searchByKeyword(keyword);
    }
    @GetMapping("/test")
    public String test() {
        return "CategoryController is working!";
    }

//    @GetMapping("/count")
//    public long count() {
//        return categoryService.countAll();
//    }
    @PostMapping("/save")
    public Category save(@RequestBody Category category) {

        return categoryService.saveCategory(category);
    }

    @GetMapping("/byName")
    public Category getByName(@RequestParam String name) {
        return categoryService.getByName(name);
    }
}
