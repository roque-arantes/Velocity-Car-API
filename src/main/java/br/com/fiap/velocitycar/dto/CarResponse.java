package br.com.fiap.velocitycar.dto;

import br.com.fiap.velocitycar.models.Car;

public record CarResponse(
        Long id,
        String brand,
        String model,
        String color,
        String chassiNumber,
        int yearManufacture,
        int yearModel,
        Double price,
        String plate
) {
    public static CarResponse fromEntity(Car car) {
        return new CarResponse(
        car.getId(),
        car.getBrand(),
        car.getModel(),
        car.getColor(),
        car.getChassiNumber(),
        car.getYearManufacture(),
        car.getYearModel(),
        car.getPrice(),
        car.getPlate()
        );
    }
}
