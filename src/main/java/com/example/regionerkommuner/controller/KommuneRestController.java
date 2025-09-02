package com.example.regionerkommuner.controller;

import com.example.regionerkommuner.model.Kommune;
import com.example.regionerkommuner.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kommuner")
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @GetMapping("getkommuner")
    public List<Kommune> getKommuner() {
        List<Kommune> lstKommune = apiServiceGetKommuner.getKommuner();
        return lstKommune;
    }


}
