package zp.brain.web_forum.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zp.brain.web_forum.model.UserEntity;
import zp.brain.web_forum.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserMessageService {

    private UserRepository userRepository;

    public UserEntity currentLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getUserEntityByUserName(auth.getName())
                .orElseThrow(()->
                        new UsernameNotFoundException
                                ("User " + auth.getName() + " was not found in the database"));
    }
}
