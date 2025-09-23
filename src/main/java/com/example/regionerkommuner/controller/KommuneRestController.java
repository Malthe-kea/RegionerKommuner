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



}
