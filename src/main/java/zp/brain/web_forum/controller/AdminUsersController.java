package zp.brain.web_forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zp.brain.web_forum.DTO.UserRoleDTO;
import zp.brain.web_forum.repository.AppRoleRepository;
import zp.brain.web_forum.repository.UserRepository;
import zp.brain.web_forum.repository.UserRoleRepository;

import static zp.brain.web_forum.model.AppRoleEntity.*;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class AdminUsersController {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private AppRoleRepository appRoleRepository;


    @GetMapping
//    @PreAuthorize("ROLE_ADMIN")
    public String showUsersList(Model model) {
        UserRoleDTO usersRole = new UserRoleDTO();
        usersRole.addUserRoles(userRoleRepository.findAll());
        model.addAttribute("listUsersRole", usersRole);
        model.addAttribute("ADMIN", appRoleRepository.getAppRoleByRoleId(ROLE_ADMIN));
        model.addAttribute("USER", appRoleRepository.getAppRoleByRoleId(ROLE_USER));
        model.addAttribute("BAN", appRoleRepository.getAppRoleByRoleId(ROLE_BAN));
        model.addAttribute("listRoles", appRoleRepository.findAll());

        return "adminUsersPage";
    }

    @PostMapping
    public String changeUserRole(@ModelAttribute("listUsersRole") UserRoleDTO userRoleDTO,
                                                                  BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/users";
        }



        return "redirect:/users";
    }


}
