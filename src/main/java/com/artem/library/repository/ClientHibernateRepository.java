package com.artem.library.repository;
import com.artem.library.config.HibernateUtil;
import com.artem.library.model.Client;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientHibernateRepository {
    private final SessionFactory sessionFactory = HibernateUtil.sf;

    public void saveClient(Client client){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }
    public List<Client> getAllClientsByName(String name) {
        Session session = sessionFactory.openSession();
        String getClientsByNameHql = "from Client where name=:name";
        Query<Client> getClientsByName = session.createQuery(getClientsByNameHql, Client.class);
        getClientsByName.setParameter("name", name);
        List<Client> clients = getClientsByName.getResultList();
        session.close();
        return clients;
    }

    public Client findById(Long id) {
        Session session = sessionFactory.openSession();
        session.setCacheMode(CacheMode.IGNORE);
        //Query<Client> getClient = session.createQuery("from Client where id=:id", Client.class);
        //getClient.setParameter("id", id);
        //Client client = getClient.getSingleResult();
        //Client client = session.get(Client.class, id);
        String hql = "select c from Client c left join fetch c.books b where c.id=:id";
        Query<Client> clientQuery = session.createQuery(hql, Client.class);
        clientQuery.setParameter("id", id);
        Client client = clientQuery.getSingleResult();
        session.close();
        return client;
    }
}
