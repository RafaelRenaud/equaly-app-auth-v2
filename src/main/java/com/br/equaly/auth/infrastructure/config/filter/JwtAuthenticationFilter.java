package com.br.equaly.auth.infrastructure.config.filter;

import com.br.equaly.auth.infrastructure.adapter.out.repository.SessionTokenRepository;
import com.br.equaly.auth.infrastructure.config.authority.EqualyGrantedAuthority;
import com.br.equaly.auth.util.ConstantsUtils;
import com.br.equaly.auth.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SessionTokenRepository sessionTokenRepository;
    private final JwtUtils jwtUtils;


    public JwtAuthenticationFilter(SessionTokenRepository sessionTokenRepository, JwtUtils jwtUtils) {
        this.sessionTokenRepository = sessionTokenRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(ConstantsUtils.TOKEN_HEADER);
        String applicationKey = request.getHeader(ConstantsUtils.APPKEY_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (applicationKey == null || applicationKey.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing x-application-key header");
                return;
            }

            Jwt jwt = jwtUtils.validateToken(token, applicationKey);

            if (jwt != null) {

                Map<String, Object> claims = jwt.getClaims();
                String username = claims.get("sub").toString();
                Object rolesClaim = claims.get("roles");

                List<GrantedAuthority> authorities = new ArrayList<>();

                if(rolesClaim instanceof List) {
                    List<Map<String, Object>> rolesList = (List<Map<String, Object>>) rolesClaim;
                    for (Map<String, Object> role : rolesList) {
                        String roleName = (String) role.get("name");
                        String roleType = (String) role.get("type");
                        authorities.add(new EqualyGrantedAuthority(roleName, roleType));
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Roles for User");
                    return;
                }

                JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(jwt, authorities, username);
                authenticationToken.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token/credentials");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
