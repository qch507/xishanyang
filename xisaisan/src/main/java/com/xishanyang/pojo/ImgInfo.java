/**
 * 
 */
package com.xishanyang.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author qiuch
 * 
 */
@Entity
@Table(name = "t_img_info")
public class ImgInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false)
	private long id;

	@Column(name = "url")
	private String url;// 标题

	@Column(name = "theme")
	private String theme;// 主题

	@Column(name = "name")
	private String name;// 发布人

	@Column(name = "create_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date create_time;// 创建时间
	@Transient
	private List<String> imgs;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

}
