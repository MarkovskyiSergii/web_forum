package zp.brain.web_forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logoutSuccess")
    public String getLogout() {
        return "logoutSuccess";
    }
}
