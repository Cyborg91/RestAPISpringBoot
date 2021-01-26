package com.sayhi.user.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.sayhi.user.model.M_user;
import com.sayhi.user.model.ResponseDasar;
import com.sayhi.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @GetMapping("")
    public ResponseDasar<List<M_user>>  list() {
        List<M_user>  x = userService.listAllUser();
        return new ResponseDasar<List<M_user>>(true, "Sukses Mengambil Data", x);
    }

    @GetMapping("/{id}")
    public ResponseDasar<M_user> get(@PathVariable long id) {
        try {
            M_user user = userService.getUser(id);
            return new ResponseDasar<M_user>(true,"Data Ada",user);
        } catch (NoSuchElementException e) {
            return new ResponseDasar<M_user>(false,"Data Gak Ada",null);
        }
    }
    @PostMapping("/")
    public ResponseDasar<M_user> add(@RequestBody M_user user) {
        try{

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return new ResponseDasar<M_user>(true,"Sukses Menyimpan Data",userService.saveUser(user));

        }catch(Exception e){
            return new ResponseDasar<M_user>(true,"Gagal Menyimpan : " + e.toString(),null);

        }
    }
    @PutMapping("/{id}")
    public ResponseDasar<M_user> update(@RequestBody M_user user, @PathVariable long id) {
        try {
            // M_user existUser = userService.getUser(id);
            if (userService.getUser(id)==null){
                return new ResponseDasar<M_user>(false, "Data Tidak Ditemukan", user);
            }
            if (user.getPassword()!=null){
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            }
            user.setId(id);            
            return new ResponseDasar<M_user>(true, "Berhasil Merubah Data", userService.saveUser(user));
        } catch (NoSuchElementException e) {
            return new ResponseDasar<M_user>(false, e.toString(), user);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseDasar<String> delete(@PathVariable long id) {
        try{
            userService.deleteUser(id);

            return new ResponseDasar<String>(true, "Sukses Menghapus Data " + id, "");
        
        
        }catch(Exception e){
            return new ResponseDasar<String>(false, "Gagal Menghapus Data "+ e.toString(), "");
    
        }
        

    }
}
