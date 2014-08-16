package uz.micros.jstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.entity.store.Author;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView home(HttpSession session) {
        Author a = new Author();
        a.setName("Akbar");

        List<Author> list;
        if (session.getAttribute("authors") == null) {
            list = new ArrayList<>();
            list.add(a);
            list.add(a);
            list.add(a);
            session.setAttribute("authors", list);
        } else
            list = (List<Author>) session.getAttribute("authors");

        return new ModelAndView("home")
                .addObject("name", "John")
                .addObject("author", a)
                .addObject("authors", list)
                .addObject("newAuthor", new Author());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam String title, HttpSession session) {
        List<Author> list = (List<Author>) session.getAttribute("authors");

        Author a = new Author();
        a.setName(title);

        list.add(a);

        return "redirect:/";
    }

    @RequestMapping(value = "/save2", method = RequestMethod.POST)
    public String save2(@ModelAttribute("newAuthor") Author newAuthor, HttpSession session) {
        List<Author> list = (List<Author>) session.getAttribute("authors");

        list.add(newAuthor);

        return "redirect:/";
    }
}