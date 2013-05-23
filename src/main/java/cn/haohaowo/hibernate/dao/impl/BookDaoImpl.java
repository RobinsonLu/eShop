package cn.haohaowo.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.haohaowo.common.Page;
import cn.haohaowo.entity.Book;
import cn.haohaowo.hibernate.dao.BookDao;
import cn.haohaowo.util.StringUtils;

@Repository("bookDao")
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
	private StringBuffer hql = null;
	
	public Book getBook(Integer id) {
		Book book = (Book)getHibernateTemplate().load(Book.class, id);
		return book;
	}
	
	public Page<Book> getBooks(String type, String title, String author,
			String publisher, String minprice, String maxprice, int pageNo,
			int pageSize) {
		Page<Book> page = new Page<Book>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		HashMap<String, Object> condition = new HashMap<String, Object>();
		
		hql = new StringBuffer("from Book b where b.published = '1'");
		
		if (StringUtils.isNotEmpty(type)) {
			hql.append(" and b.type = :type ");
			condition.put("type", type);
		}
		if (StringUtils.isNotEmpty(title)) {
			hql.append(" and b.title like :title ");
			condition.put("title", StringUtils.likeString(title));
		}
		if (StringUtils.isNotEmpty(author)) {
			hql.append(" and b.author like :author ");
			condition.put("author", StringUtils.likeString(author));
		}
		if (StringUtils.isNotEmpty(publisher)) {
			hql.append(" and b.publisher like :publisher ");
			condition.put("publisher", StringUtils.likeString(publisher));
		}
		if (StringUtils.isNotEmpty(minprice)) {
			hql.append(" and b.price >= :minprice ");
			condition.put("minprice", minprice);
		}
		if (StringUtils.isNotEmpty(maxprice)) {
			hql.append(" and b.price <= :maxprice ");
			condition.put("maxprice", maxprice);
		}
		hql.append(" order by b.publishedDate desc ");
		page.setCondition(condition);
		page.setHql(hql.toString());
		return findPage(page);
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getHotBooks() {
		hql = new StringBuffer("from Book b where b.published = :published order by b.publishedDate desc");
		List<Book> books = (List<Book>)getSession().createQuery(hql.toString()).setParameter("published", "1").setMaxResults(3).list();
		return books;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getRelativeBooks(Integer id) {
		hql = new StringBuffer("from Book b inner join fetch b.orderdetailslist d where d.order.id in"+
			"(select od.order.id from OrderDetail od where od.book.id = :id) " +
			"and b.id <> :id and b.published = true order by b.publishedDate desc");
		List<Book> books = (List<Book>) getSession().createQuery(hql.toString()).setParameter("id", id).setMaxResults(10).list();
		return books;
	}
	
	public Page<Book> search(String keyword, int pageNo, int pageSize) {
		Page<Book> page = new Page<Book>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		HashMap<String, Object> condition = new HashMap<String, Object>();
		
		hql = new StringBuffer("from Book b where b.published = '1'");
		
		if (condition.containsKey("title")) {
			hql.append(" and b.title like :title ");
			condition.put("title", StringUtils.likeString((String)condition.get("title")));
		}
		if (condition.containsKey("author")) {
			hql.append(" and b.author like :author ");
			condition.put("author", StringUtils.likeString((String)condition.get("author")));
		}
		if (condition.containsKey("description")) {
			hql.append(" and b.description like :description ");
			condition.put("description", StringUtils.likeString((String)condition.get("description")));
		}
		hql.append("order by b.publishedDate desc");
		page.setCondition(condition);
		page.setHql(hql.toString());
		
		return findPage(page);
	}
	

}
