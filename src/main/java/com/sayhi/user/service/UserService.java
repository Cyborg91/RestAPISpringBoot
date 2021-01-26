package com.sayhi.user.service;

import java.util.List;

import javax.transaction.Transactional;

import com.sayhi.user.model.M_user;
import com.sayhi.user.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<M_user> listAllUser() {
        return userRepo.findAll();
    }

    public M_user saveUser(M_user user) {
        return userRepo.save(user);
    }

    public M_user getUser(Long id) {
        return userRepo.findById(id).get();
    }

    public M_user getUserbyEmail(String email) {
        return userRepo.findByEmail(email).get();
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    
}
