package com.synechron.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByBookName(String name);

}
