package com.sayhi.user.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayhi.user.config.SecurityConstant;
import com.sayhi.user.model.M_user;
import com.sayhi.user.repository.UserRepo;
import com.sayhi.user.service.UserDetailsServiceImpl;
import com.sayhi.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UserDetailsServiceImpl userService;

    public AuthenticationFilter(AuthenticationManager authenticationManager,ApplicationContext ctx)
    {
        this.userService = ctx.getBean(UserDetailsServiceImpl.class);
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                try
                {
                    M_user creds = new ObjectMapper().readValue(request.getInputStream(), M_user.class);
                    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),new ArrayList<>()));
                }
                catch(IOException e)
                {
                    throw new RuntimeException("Could not read request" + e);
                }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication)
    {
        String token = Jwts.builder()
                .setSubject(((User)authentication.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET.getBytes())
                .compact();
                System.out.println(token);
        
        try {
            M_user user_login = userService.getUserbyEmail(((User)authentication.getPrincipal()).getUsername());
            // user_login.setToken(token);
            userService.save(user_login,token);
            response.addHeader("Authorization","Bearer " + token);
        } catch (Exception e) {
            //TODO: handle exception
            // response.addHeader("Authorization","Bearer " + token);
            System.out.println(e.toString());
            response.setHeader("Error", "Ga Bisa Login");
        }
        try {
            response.getWriter().write(token);
            response.getWriter().flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       


    }
    
}
