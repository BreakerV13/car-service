package com.carservice.repository;

import com.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM car WHERE (:brand IS NULL OR brand LIKE %:brand%) AND (:model IS NULL OR model LIKE %:model%)", nativeQuery = true)
    List<Car> findByBrandContainingOrModelContaining(String brand, String model);
}