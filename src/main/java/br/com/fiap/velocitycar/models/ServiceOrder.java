package br.com.fiap.velocitycar.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ordemServico")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ElementCollection
    @CollectionTable(
            name = "service_order_car_parts",
            joinColumns = @JoinColumn(name = "service_order_id")
    )
    @Builder.Default
    private List<CarPart> carParts = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Double totalPrice = 0.0;

    @PrePersist
    @PreUpdate
    public void updateTotalPrice() {
        if (carParts == null || carParts.isEmpty()) {
            totalPrice = 0.0;
            return;
        }

        totalPrice = carParts.stream()
                .mapToDouble(part -> {
                    if (part.getPrice() == null || part.getQuantity() == null) {
                        throw new IllegalStateException("Every car part must have price and quantity");
                    }
                    return part.getPrice() * part.getQuantity();
                })
                .sum();
    }

}
