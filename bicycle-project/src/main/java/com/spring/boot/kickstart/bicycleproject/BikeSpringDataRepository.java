package com.spring.boot.kickstart.bicycleproject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.kickstart.bicycleproject.entity.Bike;

@Repository
interface BikeSpringDataRepository extends JpaRepository<Bike, Integer> {

    public Optional<List<Bike>> findAllByBrand(String brand);

}