package com.spring.boot.kickstart.bicycleproject;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.kickstart.bicycleproject.entity.Bike;
import com.spring.boot.kickstart.bicycleproject.entity.Bill;

public interface BillDataRepository extends JpaRepository<Bill, Integer> {

    public Optional<List<Bill>> findAllByDateOfPurchase(LocalDate localDate);

}
