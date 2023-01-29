package com.artem.library.service;

import com.artem.library.model.Book;
import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public List<Book> getBooksByTitle(String title);
    public Book getBookById(Long id);
    public void saveOrUpdateBook(Book book);
    public void deleteBookById(Long id);
    public void setLockedState(Long id, boolean state);
    public void setClientId(Long id, Long bookId);
    public Book returnBookById(Long id, Long bookId);
}
