package com.synechron.book.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.synechron.book.model.Book;
import com.synechron.book.service.BookService;
import com.synechron.book.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	BookService BookService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Books---------------------------------------------

	@RequestMapping(value = "/Book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> Books = BookService.findAllBooks();
		if (Books.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Book>>(Books, HttpStatus.OK);
	}

	// -------------------Retrieve Single Book------------------------------------------

	@RequestMapping(value = "/Book/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@PathVariable("id") long id) {
		logger.info("Fetching Book with id {}", id);
		Book Book = BookService.findById(id);
		if (Book == null) {
			logger.error("Book with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Book with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(Book, HttpStatus.OK);
	}

	// -------------------Create a Book-------------------------------------------

	@RequestMapping(value = "/Book/", method = RequestMethod.POST)
	public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Book : {}", book);

		if (BookService.isBookExist(book)) {
			logger.error("Unable to create. A Book with name {} already exist", book.getBookName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Book with name " + 
			book.getBookName() + " already exist."),HttpStatus.CONFLICT);
		}
		book.setPublichDate(new Date());
		book.setBooksStock(0);
		BookService.saveBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/Book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Book ------------------------------------------------

	@RequestMapping(value = "/Book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		logger.info("Updating Book with id {}", id);

		Book currentBook = BookService.findById(id);

		if (currentBook == null) {
			logger.error("Unable to update. Book with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentBook.setAutherName(book.getAutherName());
		currentBook.setBookCategory(book.getBookCategory());
		currentBook.setBookName(book.getBookName());
		currentBook.setIsbnCode(book.getIsbnCode());
		currentBook.setNoOfBooks(book.getNoOfBooks());
		currentBook.setPublichDate(new Date());

		BookService.updateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Book/{id}/{txType}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBookCount(@PathVariable("id") long id,@PathVariable("txType") String txType) {
		logger.info("Updating Book with id {}", id);

		Book currentBook = BookService.findById(id);
		double stock=0;
		if("Issue".equals(txType))
		{
			stock =currentBook.getBooksStock() + 1;
			if(stock > currentBook.getNoOfBooks())
			{
				logger.error("Out of stock");
				return new ResponseEntity(new CustomErrorType("Out of stock"),
						HttpStatus.NOT_FOUND);
			}
		}else
		{
			stock =currentBook.getBooksStock() - 1;
			if(stock < 0)
			{
				logger.error("No more items to return");
				return new ResponseEntity(new CustomErrorType("All Books are received"),
						HttpStatus.NOT_FOUND);
			}
		}
		currentBook.setPublichDate(new Date());
		currentBook.setBooksStock(stock);
		BookService.updateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}

	// ------------------- Delete a Book-----------------------------------------

	@RequestMapping(value = "/Book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Book with id {}", id);

		Book book = BookService.findById(id);
		if (book == null) {
			logger.error("Unable to delete. Book with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		BookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Books-----------------------------

	@RequestMapping(value = "/Book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBooks() {
		logger.info("Deleting All Books");

		BookService.deleteAllBooks();
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

}