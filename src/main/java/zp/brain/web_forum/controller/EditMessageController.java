package zp.brain.web_forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zp.brain.web_forum.model.MessageEntity;
import zp.brain.web_forum.repository.MessageRepository;
import zp.brain.web_forum.repository.TopicRepository;

@Controller
@RequestMapping("/messages/editMessage/{categoryId}/{topicId}/{messageId}")
public class EditMessageController {
    private MessageRepository messageRepository;
    private TopicRepository topicRepository;
    private MessageEntity messageEntity;

    public EditMessageController(MessageRepository messageRepository, TopicRepository topicRepository) {
        this.messageRepository = messageRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping
    public String getFormEditorMessage(@PathVariable("categoryId") Long categoryId,
                                       @PathVariable("topicId") Long topicId,
                                       @PathVariable("messageId") Long messageId,
                                       Model model) {
        messageEntity = messageRepository.getMessageEntityByMessageId(messageId);
        model.addAttribute("message", messageEntity);
        model.addAttribute("titleTopic", topicRepository.getTitleByTopicId(topicId));
        model.addAttribute("textMessage", messageRepository.getTextMessageByMessageId(messageId));
        return "editorMessage";
    }

    @PostMapping
    String editMessage(@PathVariable("categoryId") Long categoryId,
                       @PathVariable("topicId") Long topicId,
                       @ModelAttribute("message") MessageEntity message,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "/messages/editMessage/{categoryId}/{topicId}/{messageId}";
        }
        Long id = messageEntity.getMessageId();
        String text = message.getTextMessage();
        messageRepository.saveEditedTextMessage(id, text);
        messageEntity = null;

        return "redirect:/messages/" + categoryId + "/" + topicId;
    }
}
