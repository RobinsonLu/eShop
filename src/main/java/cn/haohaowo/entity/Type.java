package cn.haohaowo.entity;

import java.io.Serializable;

public class Type implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3238165604126959353L;
	
	private Integer id;
	private String name;
	private Integer fid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	

}
