package com.example.regionerkommuner.controller;

import com.example.regionerkommuner.model.Kommune;
import com.example.regionerkommuner.model.Region;
import com.example.regionerkommuner.repositories.RegionRepository;
import com.example.regionerkommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/tool")
public class ToolRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("getregioner")
    public List<Region> getRegioner() {
        return apiServiceGetRegioner.getRegioner();
    }

    @DeleteMapping("deleteregion/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        Optional<Region> orgRegion = regionRepository.findById(Integer.valueOf(kode));
        if (orgRegion.isPresent()) {
            regionRepository.deleteById(Integer.valueOf(kode));
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getKommuneByRegion/{regionKode}")
    public ResponseEntity<List<String>> getKommuneByRegion(@PathVariable String regionKode) {
        Optional<Region> orgRegion = regionRepository.findById(Integer.valueOf(regionKode));
        if (orgRegion.isPresent()) {
            List<String> kommuneNames = new ArrayList<>();
            for (Kommune kommune : orgRegion.get().getKommuner()) {
                kommuneNames.add(kommune.getNavn());
            }
            return new ResponseEntity<>(kommuneNames, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @PostMapping("/region2")
    public ResponseEntity<String> postRegion2(@RequestParam String kode, @RequestParam String navn) {
        return ResponseEntity.ok("Received kode: " + kode + ", navn: " + navn);
    }

}

