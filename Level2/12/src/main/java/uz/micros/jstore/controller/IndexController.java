package uz.micros.jstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.micros.jstore.controller.store.BaseStoreController;

@Controller
public class IndexController extends BaseStoreController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }
}