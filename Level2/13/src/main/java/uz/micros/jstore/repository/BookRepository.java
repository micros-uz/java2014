package uz.micros.jstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.micros.jstore.entity.store.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getByGenreId(int id);
}
