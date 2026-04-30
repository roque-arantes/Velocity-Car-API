package br.com.fiap.velocitycar.controllers;

import br.com.fiap.velocitycar.dto.ServiceOrderRequest;
import br.com.fiap.velocitycar.models.ServiceOrder;
import br.com.fiap.velocitycar.services.ServiceOrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceOrder")
@Slf4j
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService service;

    @GetMapping
    public Page<ServiceOrder> getServiceOrder(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return service.getAllServiceOrders(PageRequest.of(page, size));
    }

    @GetMapping("/filter/total-price")
    public Page<ServiceOrder> filterServiceOrdersByTotalPrice(@RequestParam Double min,
                                                              @RequestParam Double max,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        return service.filterServiceOrdersByTotalPrice(min, max, PageRequest.of(page, size));
    }

    @PostMapping
    public ResponseEntity<ServiceOrder> createServiceOrder(@Valid @RequestBody ServiceOrderRequest serviceOrderRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addServiceOrder(serviceOrderRequest.toEntity()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrder> getServiceOrderById(@PathVariable Long id){
        return ResponseEntity.ok(service.getServiceOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceOrder> deleteServiceOrderById(@PathVariable Long id){
        service.deleteServiceOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrder> updateServiceOrderById(@PathVariable Long id, @Valid @RequestBody ServiceOrderRequest serviceOrderRequest){
        return ResponseEntity.ok(service.updateServiceOrder(id, serviceOrderRequest.toEntity()));
    }
}
