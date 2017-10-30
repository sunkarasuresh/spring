package com.synechron.book.service;

import java.util.List;

import com.synechron.book.model.Book;
import com.synechron.book.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("BookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository BookRepository;

	public Book findById(Long id) {
		return BookRepository.findOne(id);
	}

	public Book findByBookName(String name) {
		return BookRepository.findByBookName(name);
	}

	public void saveBook(Book Book) {
		BookRepository.save(Book);
	}

	public void updateBook(Book Book){
		saveBook(Book);
	}

	public void deleteBookById(Long id){
		BookRepository.delete(id);
	}

	public void deleteAllBooks(){
		BookRepository.deleteAll();
	}

	public List<Book> findAllBooks(){
		return BookRepository.findAll();
	}

	public boolean isBookExist(Book Book) {
		return findByBookName(Book.getBookName()) != null;
	}

}
