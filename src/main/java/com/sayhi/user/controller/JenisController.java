package com.sayhi.user.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.sayhi.user.model.M_jenis;
import com.sayhi.user.model.ResponseDasar;
import com.sayhi.user.service.JenisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jenis")
public class JenisController {
    @Autowired
    private JenisService jenisService;

    @GetMapping("")
    public ResponseDasar<List<M_jenis>>  list() {
        List<M_jenis>  x = jenisService.listAllJenis();
        return new ResponseDasar<List<M_jenis>>(true, "Sukses Mengambil Data", x);
    }

    @GetMapping("/{id}")
    public ResponseDasar<M_jenis> get(@PathVariable long id) {
        try {
            M_jenis jenis = jenisService.getJenis(id);
            return new ResponseDasar<M_jenis>(true,"Data Ada",jenis);
        } catch (NoSuchElementException e) {
            return new ResponseDasar<M_jenis>(false,"Data Gak Ada",null);
        }
    }
    @PostMapping("/")
    public ResponseDasar<M_jenis> add(@RequestBody M_jenis jenis) {
        try{
            return new ResponseDasar<M_jenis>(true,"Sukses Menyimpan Data",jenisService.saveJenis(jenis));

        }catch(Exception e){
            return new ResponseDasar<M_jenis>(true,"Gagal Menyimpan : " + e.toString(),null);

        }
    }
    @PutMapping("/{id}")
    public ResponseDasar<M_jenis> update(@RequestBody M_jenis jenis, @PathVariable long id) {
        try {
            // M_jenis existUser = jenisService.getUser(id);
            if (jenisService.getJenis(id)==null){
                return new ResponseDasar<M_jenis>(false, "Data Tidak Ditemukan", jenis);
            }
            jenis.setId(id);            
            return new ResponseDasar<M_jenis>(true, "Berhasil Merubah Data", jenisService.saveJenis(jenis));
        } catch (NoSuchElementException e) {
            return new ResponseDasar<M_jenis>(false, e.toString(), jenis);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseDasar<String> delete(@PathVariable long id) {

        try{
            jenisService.deleteJenis(id);

            return new ResponseDasar<String>(true, "Sukses Menghapus Data " + id, "");
    
        }catch(Exception e){
            return new ResponseDasar<String>(false, "Gagal Menghapus Data "+ e.toString(), "");
    
        }
        
        

    }
}
