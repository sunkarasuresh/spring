package com.synechron.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.synechron.book.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.synechron.book"}) 
public class BookTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTransactionApplication.class, args);
	}
}
