package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import zp.brain.web_forum.repository.UserRepository;

@Controller
@AllArgsConstructor
public class HomeController {
    private UserRepository userRepository;

    @GetMapping ("/")
    public String getHomePage( Model model) {

        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is index page");
        return "indexPage";
    }
}
