package uz.micros.jstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.micros.jstore.entity.store.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
