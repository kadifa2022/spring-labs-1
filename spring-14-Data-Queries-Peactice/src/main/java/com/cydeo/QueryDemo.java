package com.cydeo;

import com.cydeo.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class QueryDemo implements CommandLineRunner {
    private final RegionRepository regionRepository;

    public QueryDemo(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }




    @Override
    public void run(String... args) throws Exception { //run method will execute first


        System.out.println("findByCountry: " + regionRepository.findByCountry("Canada"));

    }



}
