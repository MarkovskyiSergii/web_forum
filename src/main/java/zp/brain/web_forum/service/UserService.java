package zp.brain.web_forum.service;

import zp.brain.web_forum.DTO.UserDTO;
import zp.brain.web_forum.model.UserEntity;

public interface UserService {
    UserEntity registerNewUserAccount(UserDTO accountDTO);
}
