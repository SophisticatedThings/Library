package com.artem.library.controllers;


import com.artem.library.exception_handling.ClientHandling.NoClientsException;
import com.artem.library.exception_handling.ClientHandling.NoSuchClientException;
import com.artem.library.exception_handling.NoRequestBodyException;
import com.artem.library.model.Book;
import com.artem.library.model.Client;
import com.artem.library.model.Status;
import com.artem.library.service.BookService;
import com.artem.library.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {

    final ClientService clientService;
    final BookService bookService;

    @Autowired
    public ClientRestController(ClientService clientService,BookService bookService) {
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('clients:read')")
    public List<Client> findAll(){

        if(clientService.findAllClients().isEmpty())
              throw new NoClientsException("No clients in database");
        return clientService.findAllClients();


    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('clients:read')")
    public Client findById(@PathVariable("id")Long id) {

        if( clientService.findById(id) == null){
            throw new NoSuchClientException("There is no client with id = " + id);
        }

        return clientService.findById(id);

    }
    @PostMapping
    @PreAuthorize("hasAuthority('clients:write')")
    public ResponseEntity<Client> SaveOrUpdateClient( @RequestBody Client client ){

        if (client == null) {
              throw  new NoRequestBodyException("You have not sent a client to update/save");
        }
        if(client.getStatus() == null){
            client.setStatus(Status.ACTIVE);
        }
        if(client.getId() == null)
            client.setPassword(new BCryptPasswordEncoder(12).encode(client.getPassword()));
        clientService.saveOrUpdateClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('clients:write')")
    public ResponseEntity<Client> deleteById(@PathVariable("id") Long id ){

        Client client = clientService.findById(id);
        if(client == null){
            throw new NoSuchClientException("There is no client with id = " + id + " which you want to remove");
        }
        List<Book> books = client.getBooks();
        for (Book book:
             books) {
                 bookService.setClientId(null,book.getId() );
                 bookService.setLockedState(book.getId(), false);
        }
        clientService.deleteClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }





}