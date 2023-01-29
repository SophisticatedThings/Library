package com.artem.library.service;

import com.artem.library.model.Book;
import com.artem.library.model.Client;
import com.artem.library.model.Status;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    public List<Client> findAllClients();
    public Client findById(Long id);
    public void saveOrUpdateClient(Client client);
    public void deleteClientById(Long id);


}
