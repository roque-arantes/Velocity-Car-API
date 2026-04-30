package br.com.fiap.velocitycar.dto;

import br.com.fiap.velocitycar.models.Car;
import br.com.fiap.velocitycar.models.CarPart;
import br.com.fiap.velocitycar.models.Client;
import br.com.fiap.velocitycar.models.ServiceOrder;
import br.com.fiap.velocitycar.validation.ServiceOrderValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@ServiceOrderValidation
public record ServiceOrderRequest(

        @NotBlank
        String description,

        @NotNull
        @Positive
        Long clientId,

        @NotNull
        @Positive
        Long carId,

        List<CarPart> carParts

) {
        public ServiceOrder toEntity() {
            return ServiceOrder.builder()
                    .description(description)
                    .client(Client.builder().id(clientId).build())
                    .car(Car.builder().id(carId).build())
                    .carParts(carParts != null ? carParts : new ArrayList<>())
                    .build();
        }
}
