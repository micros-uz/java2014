package uz.micros.jstore.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import uz.micros.jstore.entity.store.Author;
import uz.micros.jstore.service.store.AuthorService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

public class AuthorFormatter implements Formatter<Author> {

    @Autowired
    private AuthorService service;

    @Override
    public Author parse(String s, Locale locale) throws ParseException {
        Collection<Author> authors = service.getAuthors();
        for (Author a : authors) {
            if (a.getName().equals(s)) {
                return a;
            }
        }
        throw new ParseException("author not found: " + s, 0);
    }

    @Override
    public String print(Author author, Locale locale) {
        return author.getName();
    }
}
