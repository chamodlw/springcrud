package com.example.springcrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.springcrud.repo.book_repo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.springcrud.model.book;




@RestController

public class bookcontroller {

    @Autowired
    private book_repo book_repo;

    // Get all books
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<book>> getAllBooks() {
        try{
            List<book> bookList = new ArrayList<>();
            book_repo.findAll().forEach(bookList::add);
            if(bookList.isEmpty()) {
                return new ResponseEntity<>(bookList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Get book by id
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<book> getBookById(@PathVariable long id) {
        Optional<book> bookData = book_repo.findById(id);

        if(bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Add book
    @PostMapping("/addBook")
    public ResponseEntity<book> addBook(@RequestBody book book1) {
            book bookobj = book_repo.save(book1);
            return new ResponseEntity<>(bookobj, HttpStatus.OK);

    }

    // Update book
    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<book> updateBookById(@PathVariable long id, @RequestBody book newBookData) {
        Optional<book> oldbookData = book_repo.findById(id);
        if(oldbookData.isPresent()) {
            book updatedBookData = oldbookData.get();
            updatedBookData.setTitle(newBookData.getTitle());
            updatedBookData.setAuthor(newBookData.getAuthor());
            book bookObj = book_repo.save(updatedBookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete book by id
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable long id) {
        book_repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
