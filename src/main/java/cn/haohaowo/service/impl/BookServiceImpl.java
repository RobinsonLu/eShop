package cn.haohaowo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Book;
import cn.haohaowo.hibernate.dao.BookDao;
import cn.haohaowo.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;

	public Book getBook(Integer id) {
		return bookDao.getBook(id);
	}

	public Page<Book> getBooks(String type, String title, String author,
			String publisher, String minprice, String maxprice, int pageNo) {
		int pageSize = 12;
		return bookDao.getBooks(type, title, author, publisher, minprice, maxprice, pageNo, pageSize);
	}

	public Page<Book> getBooks(String type, String title, String author,
			String publisher, String minprice, String maxprice, int pageNo,
			int pageSize) {
		return bookDao.getBooks(type, title, author, publisher, minprice, maxprice, pageNo, pageSize);
	}

	public List<Book> getHotBooks() {
		return bookDao.getHotBooks();
	}

	public List<Book> getRelativeBooks(Integer id) {
		return bookDao.getRelativeBooks(id);
	}

	public Page<Book> search(String keyword, int pageNo) {
		int pageSize = 5;
		return search(keyword, pageNo, pageSize);
	}

	public Page<Book> search(String keyword, int pageNo, int pageSize) {
		return bookDao.search(keyword, pageNo, pageSize);
	}

}
