package br.com.fiap.velocitycar.dto;

import br.com.fiap.velocitycar.models.Car;
import jakarta.validation.constraints.*;

public record CarRequest(
        @NotBlank
        String brand,

        @NotBlank
        String model,

        @NotBlank
        String color,

        @NotBlank
        String chassiNumber,

        @NotNull
        int yearManufacture,

        @NotNull
        int yearModel,

        @NotNull
        Double price,

        @NotBlank
        String plate
) {
    public Car toEntinty() {
        return Car.builder()
                .brand(brand)
                .model(model)
                .chassiNumber(chassiNumber)
                .yearManufacture(yearManufacture)
                .yearModel(yearModel)
                .price(price)
                .plate(plate)
                .build();
    }
}
