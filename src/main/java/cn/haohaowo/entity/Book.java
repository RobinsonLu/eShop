package cn.haohaowo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="books")
public class Book implements Serializable 
{
	private static final long serialVersionUID = -2845695093349397801L;

	private Integer id;
	private String title;
	private String author;
	private String publisher;
	private String type;
	private String description;
	private String imageUrl;
	private Double price;
	private Double orgiPrice;
	private String published;
	private Date publishedDate;
	private List<OrderDetail> orderdetailslist = new ArrayList<OrderDetail>();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name="publisher")
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="image_url")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageurl) {
		this.imageUrl = imageurl;
	}
	
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@OneToMany(mappedBy="book")
	public List<OrderDetail> getOrderdetailslist() {
		return orderdetailslist;
	}
	public void setOrderdetailslist(List<OrderDetail> orderdetailslist) {
		this.orderdetailslist = orderdetailslist;
	}
	
	@Column(name="orgi_price")
	public Double getOrgiPrice() {
		return orgiPrice;
	}
	public void setOrgiPrice(Double orgiPrice) {
		this.orgiPrice = orgiPrice;
	}
	
	@Column(name="published")
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	
	@Column(name="published_date")
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
}
