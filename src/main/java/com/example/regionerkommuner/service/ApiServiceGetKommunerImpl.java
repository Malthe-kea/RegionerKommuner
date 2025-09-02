package com.example.regionerkommuner.service;
import com.example.regionerkommuner.model.Kommune;
import com.example.regionerkommuner.repositories.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner {

    private RestTemplate restTemplate;

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String KommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    @Autowired
    KommuneRepository kommuneRepository;

    @Override
    public List<Kommune> getKommuner() {
        List<Kommune> lst = new ArrayList<>();
        ResponseEntity<List<Kommune>> regionResponse =
                restTemplate.exchange(KommuneUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>() {

                        });
        List<Kommune> kommuner = regionResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }

    private void saveKommuner(List<Kommune> kommuner) {
        kommuner.forEach(kom -> kommuneRepository.save(kom));
    }
}

