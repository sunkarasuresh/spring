package com.synechron.book.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BOOK")
public class Book implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="BOOK_NAME", nullable=false)
	private String bookName;

	@Column(name="AUTHER_NAME", nullable=false)
	private String autherName;

	@Column(name="ISBN_CODE", nullable=false)
	private String isbnCode;
	
	@Column(name="NO_OF_BOOKS", nullable=false)
	private double noOfBooks;
	
	@Column(name="PUBLISH_DATE", nullable=false)
	private Date publichDate;
	
	@Column(name="BOOK_CATEGORY", nullable=false)
	private String bookCategory;
	
	@Column(name="BOOK_STOCK", nullable=false)
	private double booksStock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAutherName() {
		return autherName;
	}

	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}

	public String getIsbnCode() {
		return isbnCode;
	}

	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
	}

	public double getNoOfBooks() {
		return noOfBooks;
	}

	public void setNoOfBooks(double noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public Date getPublichDate() {
		return publichDate;
	}

	public void setPublichDate(Date publichDate) {
		this.publichDate = publichDate;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public double getBooksStock() {
		return booksStock;
	}

	public void setBooksStock(double booksStock) {
		this.booksStock = booksStock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autherName == null) ? 0 : autherName.hashCode());
		result = prime * result + ((bookCategory == null) ? 0 : bookCategory.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(booksStock);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbnCode == null) ? 0 : isbnCode.hashCode());
		temp = Double.doubleToLongBits(noOfBooks);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publichDate == null) ? 0 : publichDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (autherName == null) {
			if (other.autherName != null)
				return false;
		} else if (!autherName.equals(other.autherName))
			return false;
		if (bookCategory == null) {
			if (other.bookCategory != null)
				return false;
		} else if (!bookCategory.equals(other.bookCategory))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (Double.doubleToLongBits(booksStock) != Double.doubleToLongBits(other.booksStock))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbnCode == null) {
			if (other.isbnCode != null)
				return false;
		} else if (!isbnCode.equals(other.isbnCode))
			return false;
		if (Double.doubleToLongBits(noOfBooks) != Double.doubleToLongBits(other.noOfBooks))
			return false;
		if (publichDate == null) {
			if (other.publichDate != null)
				return false;
		} else if (!publichDate.equals(other.publichDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", autherName=" + autherName + ", isbnCode=" + isbnCode
				+ ", noOfBooks=" + noOfBooks + ", publichDate=" + publichDate + ", bookCategory=" + bookCategory
				+ ", booksStock=" + booksStock + "]";
	}
	
	
	


}
