package uz.micros.jstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.controller.store.BaseStoreController;
import uz.micros.jstore.entity.store.Author;
import uz.micros.jstore.service.store.GenreService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseStoreController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}