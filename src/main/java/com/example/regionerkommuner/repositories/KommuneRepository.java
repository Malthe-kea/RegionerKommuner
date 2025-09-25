package com.example.regionerkommuner.repositories;

import com.example.regionerkommuner.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KommuneRepository extends JpaRepository<Kommune, String> {
}
