/**
 * 
 */
package com.xishanyang.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author qiuch
 * 
 */
@Entity
@Table(name = "t_order_info")
public class OrderInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	private long id;

	@Column(name = "name")
	private String name;// 名字

	@Column(name = "phone")
	private String phone;// 电话

	@Column(name = "comment")
	private String comment;// 备注

	@Column(name = "code")
	private String code;// 预定码

	@Column(name = "status")
	private int status;// 0已预订 1已完成

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, targetEntity = ProductInfo.class)
	@JoinColumn(name = "product_id", nullable = false, updatable = false)
	private ProductInfo productInfo;// 预定的产品

	@Column(name = "order_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date order_time;// 下单时间

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

}
