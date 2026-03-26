package br.com.fiap.velocitycar.repositories;

import br.com.fiap.velocitycar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByChassiNumber(String chassiNumber);
}
