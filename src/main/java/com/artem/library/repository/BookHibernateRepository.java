package com.artem.library.repository;

import com.artem.library.config.HibernateUtil;
import com.artem.library.model.Book;
import com.artem.library.model.Client;
import com.artem.library.model.Status;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class BookHibernateRepository {
    private final SessionFactory sessionFactory = HibernateUtil.sf;
    private final ClientHibernateRepository clientHibernateRepository;

    public List<Book> getAllBooks(){
        Session session = sessionFactory.openSession();
        String getAllBooksHql = "from Book";
        Query<Book> getBooks = session.createQuery(getAllBooksHql, Book.class);
        List<Book> books = getBooks.getResultList();
        session.close();
        return books;
    }
    public List<Book> getBooksByTitle(String title){
        Session session = sessionFactory.openSession();
        String getBooksByTitleHql = "from Book where title=:title";
        Query<Book> getBooks = session.createQuery(getBooksByTitleHql, Book.class);
        getBooks.setParameter("title", title);
        List<Book> books = getBooks.getResultList();
        session.close();
        return books;
    }
    public void saveBook(Book book) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //Client client = new Client("eqr","ewqrq","ewqr","ewr","eqr", Status.ACTIVE);
        Client client = session.get(Client.class, 10L);
        client.getBooks().add(book);
        book.setClient(client);
        session.persist(client);
        session.flush();
        transaction.commit();
        session.close();
    }
    public Book findBookById(Long id) {
        Session session = sessionFactory.openSession();
        Book book = session.load(Book.class, id);
        session.close();
        return book;
    }
    public void deleteBook(Long id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Book bookToDelete = findBookById(id);
        session.delete(bookToDelete);
        transaction.commit();
        session.close();
    }

}
