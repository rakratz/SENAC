package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
