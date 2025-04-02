package com.carservice.service;


import com.carservice.entity.Car;
import com.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RecommendationService recommendationService;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> searchCars(String brand, String model) {
        return carRepository.findByBrandContainingOrModelContaining(brand, model);
    }

    public List<Car> recommendCars(String brand, String model) {
        List<Car> cars = searchCars(brand, model);
        Set<Long> recommendation = recommendationService.recommendation(cars);

        List<Car> allById = carRepository.findAllById(recommendation);
        return allById;
    }

    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setPrice(updatedCar.getPrice());
            car.setYear(updatedCar.getYear());
            car.setDescription(updatedCar.getDescription());
            car.setSold(updatedCar.isSold());
            car.setPicture(updatedCar.getPicture());
            return carRepository.save(car);
        }).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}