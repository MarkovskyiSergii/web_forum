package zp.brain.web_forum.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import zp.brain.web_forum.DTO.UserDTO;
import zp.brain.web_forum.config.WebSecurityConfig;
import zp.brain.web_forum.model.AppRoleEntity;
import zp.brain.web_forum.model.UserEntity;
import zp.brain.web_forum.model.UserRoleEntity;
import zp.brain.web_forum.repository.AppRoleRepository;
import zp.brain.web_forum.repository.UserRepository;
import zp.brain.web_forum.repository.UserRoleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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
    private WebSecurityConfig wsc;

    @Transactional
    @Override
    public UserEntity registerNewUserAccount(UserDTO userDTO, BindingResult result) {
        UserEntity user = new UserEntity();
        if (userRepository.findByUserName(userDTO.getUserName().trim()) != null) {
            LOGGER.error("registerNewUserAccount() This user name is already taken. Use another user name.");
            result.addError(new ObjectError(userDTO.getUserName(), "This username is already taken. Use another name."));
            return user = null;
        }
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            LOGGER.error("registerNewUserAccount() This email is already taken. Use another email.");
            result.addError(new ObjectError(userDTO.getUserName(), "This email is already taken. Use another email."));
            return user = null;
        }

        user.setUserName(userDTO.getUserName().trim());
        user.setEncryptedPassword(wsc.passwordEncoder().encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setTimeRegistration(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
        AppRoleEntity appRole = appRoleRepository.getAppRoleByRoleId(ROLE_USER);
        UserRoleEntity userRole = new UserRoleEntity(user, appRole);
        userRoleRepository.save(userRole);
        return user;

    }


}
