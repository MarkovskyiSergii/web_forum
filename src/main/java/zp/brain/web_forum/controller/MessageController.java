package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zp.brain.web_forum.model.MessageEntity;
import zp.brain.web_forum.model.UserEntity;
import zp.brain.web_forum.repository.MessageRepository;
import zp.brain.web_forum.repository.TopicRepository;
import zp.brain.web_forum.service.UserMessageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private MessageRepository messageRepository;
    private TopicRepository topicRepository;
    private UserMessageService userMS;

    @GetMapping("/{categoryId}/{topicId}")
    public String getMessagesByTopicID(@PathVariable("categoryId") Long categoryId,
                                       @PathVariable("topicId") Long topicId,
                                       HttpServletRequest request,
                                       Model model) {
        model.addAttribute("topic", topicRepository.getTopicEntityByTopicId(topicId));
        model.addAttribute("message", new MessageEntity());
        model.addAttribute("messages", messageRepository.getMessageEntitiesByTopicEntityIdAndOrderByMessageId(topicId));
        model.addAttribute("categoryId", categoryId);
        if (request.getUserPrincipal()!=null) {
            model.addAttribute("user", userMS.currentLoggedUser());
        }
        return "messagesPage";
    }

    @PostMapping("/{categoryId}/{topicId}")
    public String createNewMessage(@PathVariable("categoryId") Long categoryId,
                                   @PathVariable("topicId") Long topicId,
                                   @ModelAttribute("message") @Valid MessageEntity message,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {

            return "redirect:/messages/{categoryId}/{topicId}";
        }
        UserEntity user = userMS.currentLoggedUser();
        message.setUserEntity(user);
        message.setCreateMessageTime(Timestamp.valueOf(LocalDateTime.now()));
        message.setTopicEntity(topicRepository.findTopicEntityByTopicId(topicId));
        messageRepository.save(message);
        return "redirect:/messages/" + categoryId + "/" + topicId;

    }

    @GetMapping("/deleteMessage/{categoryId}/{topicId}/{messageId}")
    public String deleteMessage(@PathVariable("categoryId") Long categoryId,
                                @PathVariable("topicId") Long topicId,
                                @PathVariable("messageId") Long messageId) {
        messageRepository.deleteMessageEntitiesByMessageId(messageId);
        return "redirect:/messages/" + categoryId + "/" + topicId;
    }
}
