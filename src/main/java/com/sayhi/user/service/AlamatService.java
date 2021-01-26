package com.sayhi.user.service;

import java.util.List;

import javax.transaction.Transactional;

import com.sayhi.user.repository.AlamatRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayhi.user.model.M_alamat;


@Service
@Transactional
public class AlamatService {
    @Autowired
    private AlamatRepo alamatRepo;
    
    public List<M_alamat> listAllAlamat() {
        return alamatRepo.findAll();
    }

    public M_alamat saveAlamat(M_alamat user) {
        return alamatRepo.save(user);
    }

    public M_alamat getAlamat(Long id) {
        return alamatRepo.findById(id).get();
    }

    public void deleteAlamat(Long id) {
        alamatRepo.deleteById(id);
    }

}
