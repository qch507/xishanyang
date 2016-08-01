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
@Table(name = "t_news_info")
public class NewsInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	private long id;

	@Column(name = "title")
	private String title;// 标题

	@Column(name = "content", length = 65535, columnDefinition="TEXT")
	private String content;// 内容

	@Column(name = "type", length = 1)
	private int type;// 分类 0 普通信息 1首页公告

	@Column(name = "create_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date create_time;// 创建时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
