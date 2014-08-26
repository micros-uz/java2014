package uz.micros.jstore.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {
    @RequestMapping("/signin")
    public String login() {
        return "account/signin";
    }
}
