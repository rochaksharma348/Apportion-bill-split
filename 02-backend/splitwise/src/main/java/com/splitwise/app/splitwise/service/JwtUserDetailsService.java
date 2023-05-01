package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dao.UserRepository;
import com.splitwise.app.splitwise.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.splitwise.app.splitwise.entity.User user1 = userRepository.findByEmail(username);
        com.splitwise.app.splitwise.entity.User user2 = userRepository.findByUsername(username);

        com.splitwise.app.splitwise.entity.User user = (user1 == null) ? user2 : user1;

        if (user != null) {
            return new User(user.getEmail(), user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public com.splitwise.app.splitwise.entity.User saveUser(UserDto userDto) {
        com.splitwise.app.splitwise.entity.User user = new com.splitwise.app.splitwise.entity.User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());

        return userRepository.save(user);
    }

}
