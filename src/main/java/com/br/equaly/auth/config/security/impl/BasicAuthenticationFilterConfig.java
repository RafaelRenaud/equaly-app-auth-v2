package com.br.equaly.auth.config.security.impl;

import com.br.equaly.auth.repository.UserRepository;
import com.br.equaly.auth.util.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Log4j2
public class BasicAuthenticationFilterConfig extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(Constants.TOKEN_PREFIX);

        //Filter for Basic Authentication
        if(token != null && token.startsWith(Constants.BASIC_PREFIX)){
            token = new String(
                    Base64.getDecoder().decode(token.substring(Constants.BASIC_PREFIX.length())),
                    StandardCharsets.UTF_8
            );


            Authentication authentication = getAuthenticationScope(token, request.getServletPath());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    public Authentication getAuthenticationScope(String token, String method){
        String login = token.substring(0, token.indexOf(":"));
        String passwordEmailOrNewPassword = token.substring(token.indexOf(":")+1);
        UserDetails user = userRepository.findByLogin(login);

        if(method.equals("/login") || method.contains("/recovery")){
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        }else{
            throw new AuthenticationCredentialsNotFoundException("Credentials not found");
        }
    }
}
