package com.sayhi.user.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_alamat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class M_alamat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false, insertable = false)
    private Timestamp created_at;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP",updatable = false, insertable = false)
    private Timestamp updated_at;

    @Column
    private long id_user;

    @Column(columnDefinition = "Text")
    private String alamat;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column(columnDefinition = "Text")
    private String Keterangan;




}
