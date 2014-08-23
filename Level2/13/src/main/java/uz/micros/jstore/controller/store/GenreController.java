package uz.micros.jstore.controller.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.entity.store.Book;
import uz.micros.jstore.service.store.BookService;
import uz.micros.jstore.service.store.GenreService;

import java.util.List;

@Controller
@RequestMapping("/store/genres")
public class GenreController extends BaseStoreController{

    @Autowired
    private BookService bookService;

    @RequestMapping("/{id}")
    public ModelAndView getBooks(@PathVariable int id){
        List<Book> books = bookService.getByGenreId(id);

        return new ModelAndView("store/books")
                .addObject("books", books)
                .addObject("genreId", id);
    }
}
