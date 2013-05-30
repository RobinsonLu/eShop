package cn.haohaowo.struts.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Book;
import cn.haohaowo.service.BookService;

public class BookAction extends BaseAction {

	private static final long serialVersionUID = -4681405306237469913L;

	private Integer id;
	private String keyword = "";
	private String type = "";
	private String title = "";
	private String author = "";
	private String publisher = "";
	private String minprice = "";
	private String maxprice = "";
	private Integer pageNo = 1;
	private Page<Book> page;
	private Book book;
	private List<Book> recommendedBooks = new  ArrayList<Book>();
	
	@Autowired
	private BookService bookService;
	
	public String list(){
		page = bookService.getBooks(type, title, author, publisher, minprice, maxprice, pageNo);
		page.setUrl(request.getRequestURI());
		
		return LIST;
	}
	
	public String search(){
		page = bookService.search(keyword, pageNo);
		page.setUrl(request.getRequestURL().toString());
		
		return SEARCH;
	}
	
	public String show(){
		book = bookService.getBook(id);
		if(null == book){
			return BOOK_NOT_FOUND;
		}else{
			recommendedBooks = bookService.getRelativeBooks(id);
			return SHOW;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getMinprice() {
		return minprice;
	}

	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}

	public String getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Page<Book> getPage() {
		return page;
	}

	public void setPage(Page<Book> page) {
		this.page = page;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getRecommendedBooks() {
		return recommendedBooks;
	}

	public void setRecommendedBooks(List<Book> recommendedBooks) {
		this.recommendedBooks = recommendedBooks;
	}
	
}
