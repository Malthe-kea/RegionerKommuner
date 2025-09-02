package com.example.regionerkommuner.repositories;

import com.example.regionerkommuner.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository <Region, Integer> {
}
