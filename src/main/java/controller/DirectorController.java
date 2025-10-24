package controller;

import model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DirectorService;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PostMapping("/save")
    public Director saveDirector(@RequestBody Director director) {
        return directorService.saveDirector(director);
    }

    @PostMapping("/update/{id}")
    public Director updateDirector(@PathVariable("id") Long id, @RequestBody Director updatedDirector) {
        return directorService.updateDirector(id ,updatedDirector);
    }

    @PostMapping("/delete/{id}")
    public String deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteDirector(id);
        return "Director deleted successfully!";
    }

    @GetMapping("/all")
    public java.util.List<Director> getAllDirectors() {
        return directorService.findAll();
    }

    @GetMapping("/get/{id}")
    public Director getDirectorById(@PathVariable("id") Long id) {
        return directorService.getById(id);
    }

    @GetMapping("/byName")
    public Director getByName(@RequestParam("firstname") String firstname) {
        return directorService.getByName(firstname);
    }


}
