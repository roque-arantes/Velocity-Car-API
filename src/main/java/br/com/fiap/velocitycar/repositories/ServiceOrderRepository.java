package br.com.fiap.velocitycar.repositories;

import br.com.fiap.velocitycar.models.ServiceOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    Page<ServiceOrder> findByTotalPriceBetween(Double min, Double max, Pageable pageable);
}
