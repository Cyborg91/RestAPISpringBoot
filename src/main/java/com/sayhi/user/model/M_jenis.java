package com.sayhi.user.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tbl_jenis")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class M_jenis  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false, insertable = false)
    private Timestamp created_at;

    @JsonIgnore
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP",updatable = false, insertable = false)
    private Timestamp updated_at;

    @Column(nullable = false)
    private String jenis;

    @Column
    private String keterangan;

    
}
