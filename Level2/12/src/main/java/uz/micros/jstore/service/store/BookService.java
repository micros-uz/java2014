package uz.micros.jstore.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.store.Book;
import uz.micros.jstore.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getByGenre(int id) {
        return bookRepository.getByGenreId(id);
    }

    public Book getById(int id) {
        return bookRepository.findOne(id);
    }
}
