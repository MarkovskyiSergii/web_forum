package zp.brain.web_forum.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zp.brain.web_forum.DTO.UserDTO;
import zp.brain.web_forum.model.UserEntity;
import zp.brain.web_forum.model.UserRoleEntity;
import zp.brain.web_forum.repository.AppRoleRepository;
import zp.brain.web_forum.repository.UserRepository;
import zp.brain.web_forum.repository.UserRoleRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static zp.brain.web_forum.model.AppRoleEntity.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private AppRoleRepository appRoleRepository;
    private UserRoleRepository userRoleRepository;


    @Transactional
    @Override
    public UserEntity registerNewUserAccount(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            LOGGER.info("This email is already taken. Use another email.", userDTO.getEmail());
            throw new RuntimeException("This email is already taken. Use another email.");
        }

        UserEntity user = new UserEntity();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setEncryptedPassword(userDTO.getPassword());
        user.setTimeRegistration(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        userRoleRepository.save(new UserRoleEntity(user, appRoleRepository.getAppRoleByRoleId(ROLE_USER)));
        LOGGER.error("UserDTO don't set to USER_ENTITY", userDTO);
        return user;

    }
}
