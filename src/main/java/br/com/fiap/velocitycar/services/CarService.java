package br.com.fiap.velocitycar.services;

import br.com.fiap.velocitycar.models.Car;
import br.com.fiap.velocitycar.repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarService {
    
    @Autowired
    public CarRepository repository;

    public Car addCar(Car car) {
        if (repository.existsByChassiNumber(car.getChassiNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ChassiNumber already exists");
        }
        return repository.save(car);
    }

    public Page<Car> getAllCars(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Car> filterCarsByPrice(Double min, Double max, Pageable pageable) {
        return repository.findByPriceBetween(min, max, pageable);
    }

    public Page<Car> filterCarsByColor(String color, Pageable pageable) {
        return repository.findByColorIgnoreCase(color, pageable);
    }

    public Page<Car> filterCarsByYear(String direction, int page, int size) {
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by("yearManufacture").descending()
                : Sort.by("yearManufacture").ascending();
        return repository.findAll(PageRequest.of(page, size, sort));
    }

    public Car getCarById(Long id) {
        return getCarsById(id);
    }

    public void DeleteCarById(Long id) {
        getCarsById(id);
        repository.deleteById(id);
    }

    public Car updateCar(Long id, Car newCar) {
        Car car = getCarsById(id);
        BeanUtils.copyProperties(newCar, car, "id", "chassiNumber");
        return repository.save(car);
    }

    /// Puxa um carro Pelo ID.
    public Car getCarsById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with id " + id + " not found!")
        );
    }
}
