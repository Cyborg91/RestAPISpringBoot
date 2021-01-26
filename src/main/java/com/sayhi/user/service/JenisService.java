package com.sayhi.user.service;

import java.util.List;

import javax.transaction.Transactional;

import com.sayhi.user.model.M_jenis;
import com.sayhi.user.model.ResponseDasar;
import com.sayhi.user.repository.JenisRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JenisService {
    @Autowired
    private JenisRepo jenisRepo;

    

    public List<M_jenis> listAllJenis() {
        return jenisRepo.findAll();
    }

    public M_jenis saveJenis(M_jenis user) {
        return jenisRepo.save(user);
    }

    public M_jenis getJenis(Long id) {
        return jenisRepo.findById(id).get();
    }

    public void deleteJenis(Long id) {
        jenisRepo.deleteById(id);
        
    }

    
}
