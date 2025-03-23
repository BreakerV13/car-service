package com.carservice.repository;

import com.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandContainingOrModelContaining(String brand, String model);
}