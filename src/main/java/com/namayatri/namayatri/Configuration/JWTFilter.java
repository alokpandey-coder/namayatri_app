package com.namayatri.namayatri.Configuration;

import com.namayatri.namayatri.Controller.JWTService;
import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserRepository userRepository;

    public JWTFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if(token!=null && token.startsWith("Bearer ")){
            String tokenVal =token.substring(8,token.length()-1);
            String userName=jwtService.getUserName(tokenVal);

            Optional<User> opName =userRepository.findByUsername(userName);

            if(opName.isPresent()){
                User user = opName.get();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null,null);

                authToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
