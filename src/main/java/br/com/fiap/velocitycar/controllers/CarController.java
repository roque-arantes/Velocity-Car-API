package br.com.fiap.velocitycar.controllers;

import br.com.fiap.velocitycar.models.Car;
import br.com.fiap.velocitycar.services.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {
    
    @Autowired
    private CarService service;

    @GetMapping
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addCar(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCarById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id) {
        service.DeleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long id, @RequestBody Car car) {
        return ResponseEntity.ok(service.updateCar(id, car));
    }
}
