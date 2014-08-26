package uz.micros.jstore.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.entity.store.Book;
import uz.micros.jstore.entity.store.Genre;
import uz.micros.jstore.service.store.AuthorService;
import uz.micros.jstore.service.store.BookService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/store/books")
public class BooksController extends BaseStoreController{

    @Autowired
    private BookService bookSvc;

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/{id}")
    public ModelAndView details(@PathVariable int id) {

        Book book = bookSvc.getById(id);

        return book != null
            ? new ModelAndView("store/details").addObject("book", book)
            : new ModelAndView("notFound");
    }

    @RequestMapping("/create/{genreId}")
    public ModelAndView create(@PathVariable int genreId){
        Book book = new Book();
        List<Genre> genres = getGenres();

        for (int k = 0; k < genres.size(); k++)
            if (genres.get(k).getId() == genreId){
                book.setGenre(genres.get(k));
                break;
            }

        return new ModelAndView("store/editBook")
                .addObject("book",book)
                .addObject("authors", authorService.getAuthors());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(value = "book") Book book,
                             BindingResult result,
                             @RequestParam(required = false) MultipartFile file) {
        ModelAndView res = null;

        if (result.hasErrors()) {
            return new ModelAndView("store/editBook")
                    .addObject("book", book)
                    .addObject("authors", authorService.getAuthors())
                    .addObject("useSideBar", false);
        }

        try {
            book = bookSvc.save(book, (file != null) ? file.getBytes() : null);
            res = new ModelAndView("redirect:/store/books/" + book.getId());
        } catch (IOException e) {
            res = new ModelAndView("error")
                    .addObject("errorCode", 0)
                    .addObject("errorMessage", e.getMessage());
        }

        return res;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        Book book = bookSvc.getById(id);

        if (book != null) {
            return new ModelAndView("store/editBook")
                    .addObject("book", book)
                    .addObject("authors", authorService.getAuthors());
        } else
            return new ModelAndView("notFound");
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Book book = bookSvc.getById(id);

        if (book != null) {
            int genreId = book.getGenre().getId();

            bookSvc.delete(id);

            return "redirect:/store/genres/" + genreId;
        }else
            return "notFound";
    }
}
