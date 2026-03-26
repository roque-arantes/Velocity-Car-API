package br.com.fiap.velocitycar.repositories;

import br.com.fiap.velocitycar.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
