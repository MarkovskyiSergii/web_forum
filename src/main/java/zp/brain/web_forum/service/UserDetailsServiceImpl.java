package zp.brain.web_forum.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zp.brain.web_forum.model.UserEntity;
import zp.brain.web_forum.repository.AppRoleRepository;
import zp.brain.web_forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private AppRoleRepository appRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.getUserEntityByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException
                                ("User " + userName + " was not found in the database"));

        List<String> roleNames = this.appRoleRepository.getUserRoleNamesById(userEntity.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(userEntity.getUserName(), //
                userEntity.getEncryptedPassword(), grantList);

        return userDetails;
    }

}
