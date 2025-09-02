package com.example.regionerkommuner.controller;

import com.example.regionerkommuner.model.Region;
import com.example.regionerkommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tool")
public class ToolRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @GetMapping("getregioner")
    public List<Region> getRegioner() {
            List<Region> lstRegion = apiServiceGetRegioner.getRegioner();
            return lstRegion;
        }
    }

