package com.example.springcrud.repo;

import com.example.springcrud.model.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface book_repo extends JpaRepository<book, Long> {

    
}