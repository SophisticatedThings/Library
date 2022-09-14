package com.example.coollibrary.controllers;


import com.example.coollibrary.exception_handling.ClientHandling.NoClientsException;
import com.example.coollibrary.exception_handling.ClientHandling.NoSuchClientException;
import com.example.coollibrary.exception_handling.NoRequestBodyException;
import com.example.coollibrary.model.Book;
import com.example.coollibrary.model.Client;
import com.example.coollibrary.model.Status;
import com.example.coollibrary.service.BookService;
import com.example.coollibrary.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Client> findAll(){

        if(clientService.findAllClients().isEmpty())
              throw new NoClientsException("No clients in database");
        return clientService.findAllClients();


    }
    @GetMapping("/{id}")
    public Client findById(@PathVariable("id")Long id) {

        if( clientService.findById(id) == null){
            throw new NoSuchClientException("There is no client with id = " + id);
        }

        return clientService.findById(id);

    }
    @PostMapping
    public ResponseEntity<Client> SaveOrUpdateClient( @RequestBody Client client ){

        if (client == null) {
              throw  new NoRequestBodyException("You have not sent a client to update/save");
        }
        if(client.getStatus() == null){
            client.setStatus(Status.ACTIVE);
        }
        clientService.saveOrUpdateClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
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