package cn.haohaowo.service;

import java.util.List;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Book;

public interface BookService {
	
	public List<Book> getHotBooks();

	public Page<Book> search(String keyword, int pageNo);
	
	public Page<Book> search(String keyword, int pageNo, int pageSize);
	
	public Page<Book> getBooks(String type, String title, String author,
			String publisher, String minprice, String maxprice, int pageNo);
	
	public Page<Book> getBooks(String type, String title, String author,
			String publisher, String minprice, String maxprice, int pageNo, int pageSize);

	public Book getBook(Integer id);
	
	public List<Book> getRelativeBooks(Integer id);
}
