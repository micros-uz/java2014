package uz.micros.jstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.entity.Post;
import uz.micros.jstore.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndex(HttpSession session) {
        List<Post> posts = (List<Post>)session.getAttribute("allPosts");;

        ModelAndView res =  new ModelAndView("index")
                .addObject("name", "USTO");

        if (posts != null)
                res.addObject("posts", posts);

        return res;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(String title, HttpSession session){

        List<Post> allPosts = (List<Post>)session.getAttribute("allPosts");

        if (allPosts == null)
            allPosts = new ArrayList<Post>();

        Post p = new Post();
        p.setTitle(title);

        allPosts.add(p);

        session.setAttribute("allPosts", allPosts);

        return "redirect:/";
    }
}