package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.model.Book;
import com.empresa.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}
}
