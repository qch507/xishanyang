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
@Table(name = "t_feedback")
public class FeedBack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	private long id;

	@Column(name = "name")
	private String name;// 名字

	@Column(name = "phone")
	private String phone;// 手机号

	@Column(name = "address")
	private String address;// 地址

	@Column(name = "content")
	private String content;// 内容

	@Column(name = "reply")
	private String reply;// 回复

	@Column(name = "status")
	private int status;// 状态 0 不显示 1 显示

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
