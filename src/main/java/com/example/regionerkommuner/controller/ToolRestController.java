package com.example.regionerkommuner.controller;

import com.example.regionerkommuner.model.Kommune;
import com.example.regionerkommuner.model.Region;
import com.example.regionerkommuner.repositories.RegionRepository;
import com.example.regionerkommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tool")
public class ToolRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("getregioner")
    public List<Region> getRegioner() {
            List<Region> lstRegion = apiServiceGetRegioner.getRegioner();
            return lstRegion;
        }

    @GetMapping("deleteregion/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        Optional<Region> orgRegion = regionRepository.findById(Integer.valueOf(kode));
        if (orgRegion.isPresent()) {
            regionRepository.deleteById(Integer.valueOf(kode));
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    }

