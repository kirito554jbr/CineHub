package repository;

import model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFilmRepository extends JpaRepository<Film, Long> {

    Film getFilmByTitle(String title);
    List<Film> findByCategoryName(String categoryName);
//    List<Film> getFilmsByDirector_lastname(String lastname);

}
