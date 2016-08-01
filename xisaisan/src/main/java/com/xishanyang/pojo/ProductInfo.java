/**
 * 
 */
package com.xishanyang.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author qiuch
 * 
 */
@Entity
@Table(name = "t_product_info")
public class ProductInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	private long id;

	@Column(name = "name")
	private String name;// 名字

	@Column(name = "pic")
	private String pic;// 主图

	@Column(name = "intro")
	private String intro;// 简介

	@Column(name = "type")
	private String type;// 类型

	@Column(name = "create_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date create_time;// 创建时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getIntro() {
		return intro;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
