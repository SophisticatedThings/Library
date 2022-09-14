package com.example.coollibrary.service;

import com.example.coollibrary.model.Client;

import java.util.List;

public interface ClientService {

    public List<Client> findAllClients();
    public Client findById(Long id);
    public void saveOrUpdateClient(Client client);
    public void deleteClientById(Long id);


}
