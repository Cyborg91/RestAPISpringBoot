package com.sayhi.user.service;

import java.lang.StackWalker.Option;
import java.util.Optional;

import com.sayhi.user.model.M_user;
import com.sayhi.user.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo applicationUserRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<M_user> applicationUser = applicationUserRepository.findByEmail(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.get().getEmail(), applicationUser.get().getPassword(), emptyList());
    }

    public M_user save(M_user user,String token) {
		System.out.println(user.getEmail());
        user.setToken(token);
		return applicationUserRepository.save(user);
    }
    
    public M_user getUserbyEmail(String email) {
        return applicationUserRepository.findByEmail(email).get();
    }

}