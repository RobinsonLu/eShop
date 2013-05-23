package cn.haohaowo.struts.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.haohaowo.entity.Book;
import cn.haohaowo.service.BookService;

public class HomeAction extends BaseAction {

	private static final long serialVersionUID = -7581979352193617114L;

	private List<Book> hotBooks = new ArrayList<Book>();
	
	@Autowired
	private BookService bookService;
	
	@Override
	public String execute() throws Exception {
		hotBooks = bookService.getHotBooks();
		return super.execute();
	}

	public List<Book> getHotBooks() {
		return hotBooks;
	}

	public void setHotBooks(List<Book> hotBooks) {
		this.hotBooks = hotBooks;
	}
	
	
}
