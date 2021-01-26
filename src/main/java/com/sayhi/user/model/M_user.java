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

@Table(name = "tbl_user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class M_user  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false, insertable = false)
    private Timestamp created_at;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP",updatable = false, insertable = false)
    private Timestamp updated_at;

    @Column(unique = true)
    private String email;

    @Column
    private String nohp;

    @Column
    private String nama;

    @Column(columnDefinition = "Text")
    private String password;

    @Column(columnDefinition = "Text")
    private String token;

    @Column(columnDefinition = "Text")
    private String fotoktp;

    @Column
    private boolean active;

    @Column
    private long id_jenis;





}
