package br.com.fiap.velocitycar.dto;

import br.com.fiap.velocitycar.models.Client;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;


public record ClientRequest(
        @NotBlank
        String name,

        @CPF
        String cpf,

        @NotBlank
        @Size(min = 11)
        String phoneNumber,

        @Email
        String email,

        @NotBlank
        String address,

        @Size(min = 8)
        String cep,

        @NotNull
        Boolean isActive
) {
    public Client toEntity() {
        return Client.builder()
                .name(name)
                .cpf(cpf)
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .cep(cep)
                .isActive(isActive)
                .build();
    }
}