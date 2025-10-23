import model.Category;
import model.Director;
import model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import service.CategoryService;
import service.DirectorService;
import service.FilmService;

import java.time.LocalDate;

@Component
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        CategoryService categoryService = context.getBean(CategoryService.class);
        DirectorService directorService = context.getBean(DirectorService.class);
        FilmService filmService = context.getBean(FilmService.class);
        Film film = context.getBean(Film.class);

//        film.setTitle("The Nightmare Before Christmas");
//        film.setReleaseYear(1993);
//        film.setDuration(106);
//        film.setSynopsis("A stop-motion animated musical fantasy film that tells the story of Jack Skellington, the Pumpkin King of Halloween Town, who stumbles upon Christmas Town and becomes obsessed with bringing Christmas to his home. However, his well-intentioned plans lead to chaos and confusion as he tries to take over the holiday.");
//        Director director = directorService.getByName("Henry");
//        film.setDirector(director);
//        Category category =  categoryService.getByName("fantasy");
//        film.setCategory(category);
//        filmService.saveFilm(film);
//        System.out.println("Film inserted successfully!");

//        Director director = context.getBean(Director.class);
//        director.setFirstname("Henry");
//        director.setLastname("Selick");
//        director.setNationality("American");
//        director.setBirthDate(LocalDate.parse("1952-11-30"));
//        director.setBiography("Henry Selick is an American film director, producer, and writer, best known for his work in stop-motion animation. He was born on November 30, 1952, in Glen Ridge, New Jersey. Selick has directed several critically acclaimed films, including 'The Nightmare Before Christmas' (1993), 'James and the Giant Peach' (1996), and 'Coraline' (2009). His unique visual style and storytelling have made him a prominent figure in the world of animated cinema.");
//        directorService.saveDirector(director);
//        System.out.println("Director inserted successfully!");

        Category category1 = context.getBean(Category.class);
//        category1.setName("fantasy");
//        category1.setDescription("Films with magical or supernatural elements, often set in imaginary worlds.");
//        categoryService.updateCategory(3l, category1);
//        categoryService.saveCategory(category1);
//            categoryService.deleteCategory(4l);
//        System.out.println(categoryService.getCategoryById(1l));
//        System.out.println(categoryService.getByName("Action"));
//        System.out.println(categoryService.searchByKeyword("Films"));
//        System.out.println("Category inserted successfully!");
//        categoryService.getAllCategories().forEach(System.out::println);
    }
}