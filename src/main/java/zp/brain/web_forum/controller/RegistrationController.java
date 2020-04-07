package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import zp.brain.web_forum.DTO.UserDTO;
import zp.brain.web_forum.model.UserEntity;
import zp.brain.web_forum.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private UserService userService;

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUserAccount(@ModelAttribute("user") @Valid UserDTO userDTO,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOGGER.info("addUserAccount()", userDTO);
            System.out.println("BINDING RESULT ERROR");
            return "registration";
        }
        UserEntity registered = userService.registerNewUserAccount(userDTO);


        if (registered == null)
            return "registration";

        model.addAttribute("messageDTO", "UserDTO is add to DB");
        return "indexPage";
    }
}
