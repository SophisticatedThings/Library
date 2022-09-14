package com.example.coollibrary.service;


import com.example.coollibrary.model.Client;
import com.example.coollibrary.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    final  ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAllClients();
    }

    @Override
    public Client findById(Long id) {
        if(clientRepository.findById(id).isPresent())
              return clientRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public void saveOrUpdateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }




}
