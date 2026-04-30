package br.com.fiap.velocitycar.controllers;

import br.com.fiap.velocitycar.models.Car;
import br.com.fiap.velocitycar.services.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {
    
    @Autowired
    private CarService service;

    @GetMapping
    public Page<Car> getCars(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        return service.getAllCars(PageRequest.of(page, size));
    }

    @GetMapping("/filter/price")
    public Page<Car> filterCarsByPrice(@RequestParam Double min,
                                       @RequestParam Double max,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return service.filterCarsByPrice(min, max, PageRequest.of(page, size));
    }

    @GetMapping("/filter/year")
    public Page<Car> filterCarsByYear(@RequestParam(defaultValue = "asc") String direction,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return service.filterCarsByYear(direction, page, size);
    }

    @GetMapping("/filter/color")
    public Page<Car> filterCarsByColor(@RequestParam String color,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return service.filterCarsByColor(color, PageRequest.of(page, size));
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
