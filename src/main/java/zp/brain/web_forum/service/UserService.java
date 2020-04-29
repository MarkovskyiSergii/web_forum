package zp.brain.web_forum.service;

import org.springframework.validation.BindingResult;
import zp.brain.web_forum.DTO.UserDTO;
import zp.brain.web_forum.model.UserEntity;

public interface UserService {
    UserEntity registerNewUserAccount(UserDTO accountDTO, BindingResult result);
}
