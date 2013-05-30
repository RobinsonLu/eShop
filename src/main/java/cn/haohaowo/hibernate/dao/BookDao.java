package cn.haohaowo.hibernate.dao;

import java.util.List;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Book;


public interface BookDao {
	
	public List<Book> getHotBooks();

	public Page<Book> getBooks(String type, String title, String author,
			String publisher, String minprice, String maxprice, int pageNo,
			int pageSize);

	public Page<Book> search(String keyword, int pageNo, int pageSize);

	public Book getBook(Integer id);

	public List<Book> getRelativeBooks(Integer id);
}
