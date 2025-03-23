package com.carservice.service;


import com.carservice.entity.Car;
import com.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> searchCars(String query) {
        return carRepository.findByBrandContainingOrModelContaining(query, query);
    }

    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setPrice(updatedCar.getPrice());
            car.setDescription(updatedCar.getDescription());
            car.setSold(updatedCar.isSold());
            return carRepository.save(car);
        }).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}