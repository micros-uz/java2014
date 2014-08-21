package uz.micros.jstore.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.entity.store.Book;
import uz.micros.jstore.service.store.BookService;

import java.util.List;

@Controller
@RequestMapping("/store/genres")
public class GenresController extends BaseStoreController{

    @Autowired
    private BookService bookSvc;

    @RequestMapping("/{id}/**")
    public ModelAndView getBooks(@PathVariable(value = "id") int id){

        List<Book> books = bookSvc.getByGenre(id);

        return new ModelAndView("store/books")
                .addObject("genreId", id)
                .addObject("books", books);
    }
}
