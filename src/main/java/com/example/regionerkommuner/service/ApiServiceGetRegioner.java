package com.example.regionerkommuner.service;

import com.example.regionerkommuner.model.Region;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApiServiceGetRegioner {
    List<Region> getRegioner();
}
