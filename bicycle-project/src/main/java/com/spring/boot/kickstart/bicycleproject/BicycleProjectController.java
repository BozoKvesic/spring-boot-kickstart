package com.spring.boot.kickstart.bicycleproject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.kickstart.bicycleproject.entity.Bike;

@RestController
public class BicycleProjectController {

    private final BikeSpringDataRepository repository;

    BicycleProjectController(final BikeSpringDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/bikes")
    List<Bike> allBikes() {
        return this.repository.findAll();
    }

    @GetMapping("/bikes/{id}")
    Bike getOneBike(@PathVariable final int id) {
        return this.repository.findById(id).orElseThrow(() -> new BikeNotFoundException(id));
    }

    @GetMapping("/bikes/getAllBrand/{brand}")
    List<Bike> getAllByBrand(@PathVariable final String brand) {
        return this.repository.findAllByBrand(brand).orElseThrow(() -> new BikeNotFoundException(brand));
    }

    @PostMapping("/bikes")
    Bike newBike(@RequestBody final Bike newBike) {
        return this.repository.save(newBike);
    }

    @DeleteMapping("/bikes/{id}")
    void deleteBike(@PathVariable final int id) {
        this.repository.deleteById(id);
    }

    @PutMapping("/bikes/{id}")
    Bike replaceBike(@RequestBody final Bike newBike, @PathVariable int id) {

        return this.repository.findById(id).map(bike -> {
            bike.setName(newBike.getName());
            bike.setBrand(newBike.getBrand());
            bike.setPrice(newBike.getPrice());
            bike.setColor(newBike.getColor());
            return this.repository.save(bike);
        }).orElseGet(() -> {
            newBike.setId(id);
            return this.repository.save(newBike);
        });
    }

}
