package service;

import model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.IDirectorRepository;

import java.util.List;

@Service
public class DirectorService {

    @Autowired
    private IDirectorRepository directorRepository;

    @Transactional
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Transactional
    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }

    @Transactional
    public Director updateDirector( Long id,Director updatedDirector) {
        return directorRepository.findById(id).map(director -> {
            director.setFirstname(updatedDirector.getFirstname());
            director.setLastname(updatedDirector.getLastname());
            director.setNationality(updatedDirector.getNationality());
            director.setBirthDate(updatedDirector.getBirthDate());
            director.setBiography(updatedDirector.getBiography());
            return directorRepository.save(director);
        }).orElse(null);
    }


    @Transactional
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Transactional
    public Director getByName(String firstname) {
        return directorRepository.findByFirstname(firstname);
    }

    @Transactional
    public Director getById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

}
