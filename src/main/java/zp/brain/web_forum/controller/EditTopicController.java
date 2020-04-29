package zp.brain.web_forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zp.brain.web_forum.model.TopicEntity;
import zp.brain.web_forum.repository.CategoryRepository;
import zp.brain.web_forum.repository.TopicRepository;

@Controller
@RequestMapping("/topics/editTopic/{categoryId}/{topicId}")
public class EditTopicController {
    private CategoryRepository categoryRepository;
    private TopicRepository topicRepository;
    private TopicEntity topicEntity;

    public EditTopicController(CategoryRepository categoryRepository, TopicRepository topicRepository) {
        this.categoryRepository = categoryRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping
    public String getFormEditorTopic(@PathVariable("categoryId") Long categoryId,
                                       @PathVariable("topicId") Long topicId,
                                       Model model) {
        topicEntity = topicRepository.getTopicEntityByTopicId(topicId);
        model.addAttribute("topic", topicEntity);
        model.addAttribute("categoryEntity", categoryRepository.findCategoryEntityByCategoryId(categoryId));
        model.addAttribute("titleTopic", topicRepository.getTitleByTopicId(topicId));
        return "editorTopic";
    }

    @PostMapping
    String editTopic(@PathVariable("categoryId") Long categoryId,
                       @PathVariable("topicId") Long topicId,
                       @ModelAttribute("topic") TopicEntity topic,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "/topics/editTopic/{categoryId}/{topicId}";
        }
        Long id = topicEntity.getTopicId();
        String title = topic.getTitleTopic();
        topicRepository.saveEditedTitleTopic(id, title);
        topicEntity = null;

        return "redirect:/topics/" + categoryId ;
    }
}
