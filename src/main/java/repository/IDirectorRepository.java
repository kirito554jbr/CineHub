package repository;

import model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDirectorRepository extends JpaRepository<Director, Long> {
    // 1️⃣ Derived query methods (Spring auto-generates SQL)
    Director findByFirstname(String firstname);
}
