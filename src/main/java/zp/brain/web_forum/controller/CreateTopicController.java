package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zp.brain.web_forum.model.TopicEntity;
import zp.brain.web_forum.repository.CategoryRepository;
import zp.brain.web_forum.repository.TopicRepository;
import zp.brain.web_forum.service.UserMessageService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/create-topic/{categoryId}")
@AllArgsConstructor
public class CreateTopicController {

    private TopicRepository topicRepository;
    private UserMessageService userMS;
    private CategoryRepository categoryRepository;

    @GetMapping
    public String getCreateTopicForm(@PathVariable("categoryId") Long categoryId,
                                     Model model) {
        model.addAttribute("categoryEntity", categoryRepository.findCategoryEntityByCategoryId(categoryId));
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("topic", new TopicEntity());
        return "createTopic";
    }

    @PostMapping
    public String createTopic(@PathVariable("categoryId") Long categoryId,
                              @ModelAttribute("topic") @Valid TopicEntity topic,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/create-topic/{categoryId}";
        }
        topic.setUserEntity(userMS.currentLoggedUser());
        topic.setCategory(categoryRepository.findCategoryEntityByCategoryId(categoryId));
        topic.setCreateTopicTime(Timestamp.valueOf(LocalDateTime.now()));
        topicRepository.save(topic);
        model.addAttribute("topic", topic);
        return "redirect:/topics/" + categoryId;
    }


}
