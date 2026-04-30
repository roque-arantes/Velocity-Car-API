package br.com.fiap.velocitycar.services;

import br.com.fiap.velocitycar.models.ServiceOrder;
import br.com.fiap.velocitycar.repositories.ServiceOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceOrderService {

    @Autowired
    public ServiceOrderRepository repository;

    public ServiceOrder addServiceOrder (ServiceOrder serviceOrder){
        return repository.save(serviceOrder);
    }

    public Page<ServiceOrder> getAllServiceOrders(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<ServiceOrder> filterServiceOrdersByTotalPrice(Double min, Double max, Pageable pageable) {
        return repository.findByTotalPriceBetween(min, max, pageable);
    }

    public ServiceOrder getServiceOrderById(Long id){
        return getServicesOrdersById(id);
    }

    public void deleteServiceOrderById(Long id){
        getServicesOrdersById(id);
        repository.deleteById(id);
    }

    public ServiceOrder updateServiceOrder(Long id, ServiceOrder newServiceOrder){
        ServiceOrder serviceOrder = getServiceOrderById(id);
        BeanUtils.copyProperties(newServiceOrder, serviceOrder, "id");
        return repository.save(serviceOrder);
    }

    public ServiceOrder getServicesOrdersById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Order with id " + id + " not found!")
        );
    }
}
