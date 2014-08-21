package uz.micros.jstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.micros.jstore.entity.store.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
