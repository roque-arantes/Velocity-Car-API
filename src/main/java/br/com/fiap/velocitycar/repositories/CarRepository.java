package br.com.fiap.velocitycar.repositories;

import br.com.fiap.velocitycar.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByChassiNumber(String chassiNumber);

    Page<Car> findByPriceBetween(Double min, Double max, Pageable pageable);

    Page<Car> findByColorIgnoreCase(String color, Pageable pageable);

    List<Car> findAllByOrderByYearManufactureAsc();

    List<Car> findAllByOrderByYearManufactureDesc();

}
