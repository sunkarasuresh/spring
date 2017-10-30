package com.synechron.book.service;


import java.util.List;

import com.synechron.book.model.Book;

public interface BookService {
	
	Book findById(Long id);

	Book findByBookName(String name);

	void saveBook(Book Book);

	void updateBook(Book Book);

	void deleteBookById(Long id);

	void deleteAllBooks();

	List<Book> findAllBooks();

	boolean isBookExist(Book Book);
}