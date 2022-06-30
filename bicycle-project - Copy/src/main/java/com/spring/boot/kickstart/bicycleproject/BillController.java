package com.spring.boot.kickstart.bicycleproject;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.kickstart.bicycleproject.entity.Bike;
import com.spring.boot.kickstart.bicycleproject.entity.Bill;

@RestController
public class BillController {

    private final BillDataRepository repository;

    BillController(final BillDataRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/bills")
    Bill newBill(@RequestBody final List<Bike> purchasedBicycles) {
        int totalPrice = 0;
        for (Bike bike : purchasedBicycles) {
            totalPrice += bike.getPrice();
        }
        Bill bill = new Bill(LocalDate.now(), totalPrice, purchasedBicycles);
        return this.repository.save(bill);
    }

    @GetMapping("/bills")
    List<Bill> allBills(@RequestParam(value = "dateOfPurchase", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dateOfPurchase) {
        if (dateOfPurchase != null) {
            return this.repository.findAllByDateOfPurchase(dateOfPurchase).orElseThrow(BillNotFoundException::new);
        }
        return this.repository.findAll();
    }

    @GetMapping("/bills/{dateOfPurchase}")
    List<Bill> getAllBillOnDateOfPurchase(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dateOfPurchase) {
        return this.repository.findAllByDateOfPurchase(dateOfPurchase).orElseThrow(BillNotFoundException::new);
    }

    @GetMapping("/statistic")
    Map<String, Float> getStatistic(@RequestParam(value = "brend", required = false) final String brend, @RequestParam(value = "color", required = false) final String color) {
        List<Bill> bills = this.repository.findAll();
        List<Bike> bikes;
        float totalSaleCount = 0;
        float brendPercentageSale = 0;
        float colorPercentageSale = 0;
        float brendColorPercentageSale = 0;
        for (Bill bill : bills) {
            bikes = bill.getBikes();
            for (Bike bike : bikes) {
                if (bike.getBrand().equals(brend) && bike.getColor().equals(color) ) {
                    brendColorPercentageSale += bike.getPrice();
                }
                if (bike.getBrand().equals(brend)) {
                    brendPercentageSale += bike.getPrice();
                }
                if (bike.getColor().equals(color)) {
                    colorPercentageSale += bike.getPrice();
                }
            }
            totalSaleCount += bill.getTotalPrice();
        }
        Map<String, Float> map = new HashMap<>();
        if (totalSaleCount != 0) {
            map.put("Total sale count", totalSaleCount);
            if (brendColorPercentageSale != 0) {
                map.put("Color and brend percentage sale", (brendColorPercentageSale * 100) / totalSaleCount);
                return map;
            }
            if (brendPercentageSale != 0) {
                map.put("Brend percentage sale", (brendPercentageSale * 100) / totalSaleCount);
                return map;
            }
            if (colorPercentageSale != 0) {
                map.put("Color percentage sale", (colorPercentageSale * 100) / totalSaleCount);
                return map;
            }
        }
        return map;
    }

}
