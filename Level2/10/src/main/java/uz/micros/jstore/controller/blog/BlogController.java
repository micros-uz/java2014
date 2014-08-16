package uz.micros.jstore.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.micros.jstore.entity.blog.Blog;
import uz.micros.jstore.service.blog.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService service;

    @RequestMapping
    public String blogIndex(ModelMap map){

        Blog blog = service.getBlog();

        map.addAttribute("blog", blog);

        return "blog/index";
    }
}
