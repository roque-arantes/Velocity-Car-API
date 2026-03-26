package br.com.fiap.velocitycar.controllers;

import br.com.fiap.velocitycar.models.Client;
import br.com.fiap.velocitycar.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> listAll() { return service.getAllClient() ;}

    @PostMapping
    public ResponseEntity<Client> createMovie(@RequestBody Client client){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addClient(client));
    };

    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        log.info("Obtendo dados do cliente com id: {}", id);
        return ResponseEntity.ok(service.getClientById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        log.info("Deletando informações do cliente com id: {}", id);
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client){
        log.info("Atualizando cliente com id {} com os dados {}", id, client);
        return ResponseEntity.ok( service.updateClient(id, client));
    }
}
