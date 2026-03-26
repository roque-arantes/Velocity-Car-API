package br.com.fiap.velocitycar.services;

import br.com.fiap.velocitycar.models.Client;
import br.com.fiap.velocitycar.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> getAllClient() {
        return repository.findAll();
    }

    public Client addClient(Client client) {
        return repository.save(client);
    }

    public Client getClientById(Long id) { return findClientById(id); }

    public void deleteClient(Long id) {
        findClientById(id);
        repository.deleteById(id);
    }

    public Client updateClient(Long id, Client newClient) {
        findClientById(id);
        newClient.setId(id);
        return repository.save(newClient);
    }

    public Client findClientById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com id" + id + "Não encontrado")
        );
    }
}
