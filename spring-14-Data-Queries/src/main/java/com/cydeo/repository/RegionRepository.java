package com.cydeo.repository;

import com.cydeo.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    //display all regions in Canada-from DB- SELECT * WHERE(findByCountry) SEQUEL code
    List<Region> findByCountry(String Country);
   //find and get are the same
    List<Region> getByCountry(String country);

    //display all region with country name includes "United"
    List<Region> findByCountryContaining(String  country);

    //Display all regions with country name includes 'United' in order (region)
    List<Region>findByCountryContainsOrderByRegionDesc(String country);
    List<Region>findByCountryContainsOrderByRegion(String country);

    //Display top 2 regions in United States
    List<Region>findTopByCountry(String country);
    List<Region> findTop2ByCountry(String country);





}
