package repository;

import model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {


    Category findByName(String name);


    @Query(value = "SELECT * FROM category WHERE description LIKE %:keyword%", nativeQuery = true)
    List<Category> searchByDescription(@Param("keyword") String keyword);

}
