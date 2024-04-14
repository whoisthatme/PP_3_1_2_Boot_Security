package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
        private final UserDetailsService userDetailsService;

        @Autowired
        public SuccessUserHandler(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                    httpServletResponse.sendRedirect("/user");
                } else {
                    httpServletResponse.sendRedirect("/");
                }
            }
        }
}