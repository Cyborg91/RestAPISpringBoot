package com.sayhi.user.repository;

import com.sayhi.user.model.M_jenis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JenisRepo extends JpaRepository<M_jenis,Long> {
    
}
