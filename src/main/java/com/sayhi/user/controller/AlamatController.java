package com.sayhi.user.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.sayhi.user.model.M_alamat;
import com.sayhi.user.model.ResponseDasar;
import com.sayhi.user.service.AlamatService;

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
@RequestMapping("/alamat")
public class AlamatController {
    @Autowired
    private AlamatService alamatService;

    @GetMapping("")
    public ResponseDasar<List<M_alamat>>  list() {
        List<M_alamat>  x = alamatService.listAllAlamat();
        return new ResponseDasar<List<M_alamat>>(true, "Sukses Mengambil Data", x);
    }

    @GetMapping("/{id}")
    public ResponseDasar<M_alamat> get(@PathVariable long id) {
        try {
            M_alamat alamat = alamatService.getAlamat(id);
            return new ResponseDasar<M_alamat>(true,"Data Ada",alamat);
        } catch (NoSuchElementException e) {
            return new ResponseDasar<M_alamat>(false,"Data Gak Ada",null);
        }
    }
    @PostMapping("/")
    public ResponseDasar<M_alamat> add(@RequestBody M_alamat alamat) {
        try{
            return new ResponseDasar<M_alamat>(true,"Sukses Menyimpan Data",alamatService.saveAlamat(alamat));

        }catch(Exception e){
            return new ResponseDasar<M_alamat>(true,"Gagal Menyimpan : " + e.toString(),null);

        }
    }
    @PutMapping("/{id}")
    public ResponseDasar<M_alamat> update(@RequestBody M_alamat alamat, @PathVariable long id) {
        try {
            // M_alamat existAlamat = alamatService.getAlamat(id);
            if (alamatService.getAlamat(id)==null){
                return new ResponseDasar<M_alamat>(false, "Data Tidak Ditemukan", alamat);
            }
            alamat.setId(id);            
            return new ResponseDasar<M_alamat>(true, "Berhasil Merubah Data", alamatService.saveAlamat(alamat));
        } catch (NoSuchElementException e) {
            return new ResponseDasar<M_alamat>(false, e.toString(), alamat);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseDasar<String> delete(@PathVariable long id) {
        try{
            
        alamatService.deleteAlamat(id);

        return new ResponseDasar<String>(true, "Sukses Menghapus Data " + id, "");
        
        
        }catch(Exception e){
            return new ResponseDasar<String>(false, "Gagal Menghapus Data "+ e.toString(), "");
    
        }
        

    }
}
