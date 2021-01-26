package com.sayhi.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.sayhi.user.model.M_user;
import com.sayhi.user.model.ResponseDasar;
import com.sayhi.user.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping(value = "/auth")
@NoArgsConstructor
@AllArgsConstructor
public class AuthController {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @PostMapping("/signup")
    public ResponseDasar<M_user> signUp(@RequestBody M_user user)
    {
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return new ResponseDasar<M_user>(true, "Sukses Menyimpan Data", userRepository.save(user));    
        }catch(Exception e){
            return new ResponseDasar<M_user>(false, "Gagal Menyimpan Data :" + e.toString(), user);

        }
    }

}
