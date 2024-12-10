package com.ipseweb.traffic.provider;

import com.ipseweb.traffic.dto.CustomUserDetails;
import com.ipseweb.traffic.service.CustomUserDetailsService;
import com.ipseweb.traffic.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private CustomUserDetailsService userDetailsService;
    private SecurityUtil securityUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(username);
        log.info("CustomUserDetails: {}", customUserDetails);

        // request password를 해시 암호화
        String password = authentication.getCredentials().toString();
        String hashedPassword = securityUtil.hashPassword(password, customUserDetails.getUserSalt());

        // 저장된 hashed password와 비교

        if(!customUserDetails.getPassword().equals(hashedPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                customUserDetails, authentication.getCredentials(), customUserDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
