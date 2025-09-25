package com.example.regionerkommuner.controller;

import com.example.regionerkommuner.model.Kommune;
import com.example.regionerkommuner.repositories.KommuneRepository;
import com.example.regionerkommuner.service.ApiServiceGetKommuner;
import com.example.regionerkommuner.service.ApiServiceGetKommunerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/kommuner")
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;
    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("getkommuner")
    public List<Kommune> getKommuner() {
        return apiServiceGetKommuner.getKommuner();
    }

    @GetMapping("kommuner")
    public List<Kommune> fetchAllKommuner() {
        return kommuneRepository.findAll();
    }

    @DeleteMapping("deletekommune/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(kode);
        if (orgKommune.isPresent()) {
            kommuneRepository.deleteById(kode);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
