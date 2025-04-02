package com.carservice.controller;


import com.carservice.entity.Car;
import com.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private CarService carService;

    @GetMapping("/search")
    public List<Car> searchCars(@RequestParam(name = "brand", required = false) String brand,
                                @RequestParam(name = "model", required = false) String model) {
        return carService.recommendCars(brand, model);
    }
}