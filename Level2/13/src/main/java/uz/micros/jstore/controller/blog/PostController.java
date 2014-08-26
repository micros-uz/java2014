package uz.micros.jstore.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uz.micros.jstore.entity.blog.Comment;
import uz.micros.jstore.entity.blog.Post;
import uz.micros.jstore.service.blog.PostService;

@Controller
@RequestMapping("/blog/posts")
public class PostController {

    @Autowired
    private PostService service;

    @RequestMapping("/create")
    public String create(ModelMap map){
        map.addAttribute("post", new Post());

        return "blog/editPost";
    }

    @RequestMapping("/{id}")
    public ModelAndView getPost(@PathVariable int id){

        Post post = service.get(id);

        if (post != null) {
            Comment comment = new Comment();
            comment.setPost(post);

            return new ModelAndView("blog/post")
                    .addObject("post", post)
                    .addObject("newComment", comment);
        }
        else{
            return new ModelAndView("notFound");
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(ModelMap map, @PathVariable int id){

        Post post = service.get(id);

        if (post != null){
            map.addAttribute("post", post);

            return "blog/editPost";
        }else
            return "notFound";
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        service.delete(id);

        return new ModelAndView("redirect:/blog");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(value = "post") Post post){
        post = service.save(post);

        return new ModelAndView("redirect:/blog/posts/" + post.getId());
    }
}
