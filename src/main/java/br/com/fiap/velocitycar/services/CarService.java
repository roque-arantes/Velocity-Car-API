package br.com.fiap.velocitycar.services;

import br.com.fiap.velocitycar.models.Car;
import br.com.fiap.velocitycar.repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<Car> getAllCars() {
        return repository.findAll();
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
