package com.artem.library.repository;

import com.artem.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Book getBookById(Long id);
    Book findAllByAuthorAndAndTitle(String author, String title);
    List<Book> getBooksByTitle(String title);
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update books set locked=:state where id=:id")
    void setLocked(Long id, boolean state);
    @Query(nativeQuery = true, value = "select * from books")
    List<Book> findAllBooks();
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update books set client_id=:id where id=:bookId")
    void setClientId(Long id, Long bookId);

}

