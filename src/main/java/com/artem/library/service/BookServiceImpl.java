package com.artem.library.service;


import com.artem.library.model.Book;
import com.artem.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public void saveOrUpdateBook(Book book) {
         bookRepository.save(book);
    }


    @Override
    public void deleteBookById(Long id) {
         bookRepository.deleteById(id);
    }

    @Override
    public void setLockedState(Long id, boolean state) {
        bookRepository.setLocked(id, state);
    }

    @Override
    public void setClientId(Long id, Long bookId) {

        bookRepository.setClientId(id, bookId);
    }

    @Override
    public Book returnBookById(Long id, Long bookId) {

        bookRepository.setLocked(id,false);
        bookRepository.setClientId(null, id);
        return bookRepository.getBookById(bookId);

    }

}
