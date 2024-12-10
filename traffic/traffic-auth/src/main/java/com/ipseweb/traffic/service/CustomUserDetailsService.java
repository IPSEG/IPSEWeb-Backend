package com.ipseweb.traffic.service;

import com.ipseweb.traffic.dto.CustomUserDetails;
import com.ipseweb.traffic.entity.User;
import com.ipseweb.traffic.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return CustomUserDetails.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .password(user.getUserPassword())
                .userSalt(user.getUserSalt())
                .build();
    }
}
