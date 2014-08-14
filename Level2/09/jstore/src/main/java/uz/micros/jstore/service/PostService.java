package uz.micros.jstore.service;

import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    public List<Post> getPosts() {

        List<Post> res = new ArrayList<Post>();

        for (int k = 0; k < 4; k++){
            Post p = new Post();

            p.setTitle("Post# " + k);
            p.setDate(new Date());
            p.setText("wrfweklrasd;lkfsad fas df sadf asdfasdfas df asdf");

            res.add(p);
        }

        return res;
    }
}
