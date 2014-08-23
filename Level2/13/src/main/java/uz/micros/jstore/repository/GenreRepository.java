package uz.micros.jstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.micros.jstore.entity.store.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
}
