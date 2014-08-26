package uz.micros.jstore.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import uz.micros.jstore.entity.store.Genre;
import uz.micros.jstore.service.store.GenreService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

public class GenreFormatter implements Formatter<Genre> {

    @Autowired
    private GenreService service;

    @Override
    public Genre parse(String s, Locale locale) throws ParseException {
        Collection<Genre> genres = service.getGenres();
        for (Genre g : genres) {
            if (g.getTitle().equals(s)) {
                return g;
            }
        }
        throw new ParseException("genre not found: " + s, 0);
    }

    @Override
    public String print(Genre genre, Locale locale) {
        return genre.getTitle();
    }
}