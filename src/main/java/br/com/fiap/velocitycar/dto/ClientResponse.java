package br.com.fiap.velocitycar.dto;

import br.com.fiap.velocitycar.models.Client;

public record ClientResponse(
        Long id,
        String name,
        String cpf,
        String phoneNumber,
        String email,
        String address,
        String cep,
        Boolean isActive


) {
    public static ClientResponse fromEntity(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getAddress(),
                client.getCep(),
                client.getIsActive()
        );
    }
}
