package uz.micros.jstore.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.micros.jstore.entity.store.Genre;
import uz.micros.jstore.service.store.GenreService;

import java.util.List;

public abstract class BaseStoreController {
    @Autowired
    private GenreService service;

    @ModelAttribute("genres")
    public List<Genre> getGenres(){
        return service.getGenres();
    }
}
