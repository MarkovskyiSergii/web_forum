package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zp.brain.web_forum.model.CategoryEntity;
import zp.brain.web_forum.model.TopicEntity;
import zp.brain.web_forum.repository.CategoryRepository;
import zp.brain.web_forum.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CategoryController {

    private CategoryRepository categoryRepository;
    private TopicRepository topicRepository;

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("countTopic", topicRepository);
        model.addAttribute("title", "Home WebForum");
        model.addAttribute("categories",categoryRepository.findAll());
        return "indexPage";
    }

}
