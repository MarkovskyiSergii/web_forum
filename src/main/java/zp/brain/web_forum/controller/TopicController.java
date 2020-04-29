package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zp.brain.web_forum.repository.CategoryRepository;
import zp.brain.web_forum.repository.MessageRepository;
import zp.brain.web_forum.repository.TopicRepository;
import zp.brain.web_forum.service.UserMessageService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/topics")
@AllArgsConstructor
public class TopicController {
    private CategoryRepository categoryRepository;
    private TopicRepository topicRepository;
    private MessageRepository messageRepository;
    private UserMessageService userMS;

    @GetMapping("/{categoryId}")
    public String showTopics(@PathVariable("categoryId") Long categoryId,
                             HttpServletRequest request,
                             Model model) {
        model.addAttribute("title", "Topics");
        model.addAttribute("category", categoryRepository.findCategoryEntityByCategoryId(categoryId));
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("topics", topicRepository.getTopicEntitiesByCategoryIdAndOrderByTopicId(categoryId));
        model.addAttribute("countMessages", messageRepository);
        if (request.getUserPrincipal()!=null) {
            model.addAttribute("user", userMS.currentLoggedUser());
        }
        return "topicPage";
    }

    @GetMapping("/deleteTopic/{categoryId}/{topicId}")
    public String deleteTopic(@PathVariable("categoryId") Long categoryId,
                                @PathVariable("topicId") Long topicId) {
        topicRepository.deleteTopicEntityByTopicId(topicId);
        return "redirect:/topics/" + categoryId;
    }


}
