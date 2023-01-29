package com.artem.library.repository;

import com.artem.library.model.Book;
import com.artem.library.model.Client;
import com.artem.library.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value = "select * from clients")
    List<Client> findAllClients();




}
