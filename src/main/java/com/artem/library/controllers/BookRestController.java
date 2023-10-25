package com.artem.library.controllers;


import com.artem.library.exception_handling.*;
import com.artem.library.exception_handling.ClientHandling.NoSuchClientException;
import com.artem.library.exception_handling.bookHandling.BookAlreadyExists;
import com.artem.library.exception_handling.bookHandling.BookAlreadyInUsingException;
import com.artem.library.exception_handling.bookHandling.NoBooksException;
import com.artem.library.exception_handling.bookHandling.NoSuchBookException;
import com.artem.library.model.Book;
import com.artem.library.repository.BookHibernateRepository;
import com.artem.library.service.BookService;
import com.artem.library.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {
    
    private final ClientService clientService;
    private final BookService bookService;
    private final BookHibernateRepository bookHibernateRepository;

    @GetMapping(value = {"","/{title}"})
    @PreAuthorize("hasAuthority('books:read')")
    public List<Book> getAllBooks(@PathVariable(required = false) String title) {

        if(title != null){

            List<Book> booksByTitle = bookHibernateRepository.getBooksByTitle(title);
            if (booksByTitle.isEmpty()){
                throw new NoBooksException("There is no books with title = " + title);
            }
            return booksByTitle;

        }
        List<Book> books = bookHibernateRepository.getAllBooks();

        if (books.isEmpty()) {
            throw new NoBooksException("The library is empty currently");
        }
        else {
            return books;
        }

    }
    /*@GetMapping("/{id}")
    @PreAuthorize("hasAuthority('books:read')")
    public Book getBookById(@PathVariable("id")Long id){

            Book book = bookService.getBookById(id);
            if(book == null){
                throw new NoSuchBookException("There is no book with id = " + id );
            }
            else{
                return book;
            }
    }*/
    @PatchMapping("/rent/{id}/")
    //@PreAuthorize("hasAuthority('books:write')")
    public Book rentBookById( @PathVariable("id") Long id, @RequestParam(value="bookId", required = false) Long bookId){

          if (clientService.findById(id) == null){
               throw new NoSuchClientException("There is no client with id = " + id);
          }
          Book book = bookService.getBookById(bookId);
          if(book == null) {
               throw new NoSuchBookException(" There is no book with id = " + bookId);

          }
          if(book.isLocked()){
               throw new BookAlreadyInUsingException("Book with id = " + bookId +  " is in using ");
          }
          else{
              bookService.setLockedState(bookId, true);
              bookService.setClientId(id, bookId);
              return book;
          }


    }
    @PostMapping
    @PreAuthorize("hasAuthority('books:write')")
    public ResponseEntity<Book> saveOrUpdateBook( @RequestBody  Book addedBook) {
        if (addedBook == null) {
            throw  new NoRequestBodyException("You have not sent a book to update/save");
        }
        else if (addedBook.getId() != null){
            this.bookService.saveOrUpdateBook(addedBook);
            return new ResponseEntity<>(addedBook, HttpStatus.OK);
        }
        else {
            List<Book> books = bookService.getAllBooks();
            if(books.isEmpty()) {
                this.bookService.saveOrUpdateBook(addedBook);
                return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
            }
            for (Book book :
                    books) {
                if (addedBook.equals(book)) {

                      throw  new BookAlreadyExists("The book is already exists in library, so you can't add it");
                }
            }
                this.bookService.saveOrUpdateBook(addedBook);
                return new ResponseEntity<>(addedBook, HttpStatus.CREATED);// add the book
            }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('books:write')")
    public Book deleteBookById(@PathVariable("id") Long id){
        Book book = bookService.getBookById(id);
        if(book == null) {
            throw new NoSuchBookException(" There is no book with id = " + id);
        }
        else {
            bookService.deleteBookById(id);
            return book;
        }
    }   
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('books:write')")
    public Book returnBookById(@PathVariable("id") Long id){
        Book book = bookService.getBookById(id);
        if(book == null) {
            throw new NoSuchBookException(" There is no book with id = " + id);
        }
        return bookService.returnBookById(id, book.getId());
    }

}
