package br.com.fiap.velocitycar.dto;

import br.com.fiap.velocitycar.models.CarPart;
import br.com.fiap.velocitycar.models.ServiceOrder;

import java.util.List;

public record ServiceOrderResponse(
        Long id,
        String description,
        Long clientId,
        Long carId,
        List<CarPart> carParts,
        Double totalPrice

) {
    public static ServiceOrderResponse fromEntity(ServiceOrder serviceOrder) {
        return new ServiceOrderResponse(
                serviceOrder.getId(),
                serviceOrder.getDescription(),
                serviceOrder.getClient() != null ? serviceOrder.getClient().getId() : null,
                serviceOrder.getCar() != null ? serviceOrder.getCar().getId() : null,
                serviceOrder.getCarParts(),
                serviceOrder.getTotalPrice()
        );
    }
}
