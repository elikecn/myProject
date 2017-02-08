package com.ixiaofan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cs_audio")
public class Audio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 歌名
	 */
	@Column(name = "au_title", length = 20)
	private String title;
	
	/**
	 * 歌手
	 */
	@Column(name = "au_singer", length = 20)
	private String singer;
	
	/**
	 * 文件存在校验
	 */
	@ManyToOne
	@JoinColumn(name = "au_md5")
	private FileMD5 md5;
	
	/**
	 * 备注
	 */
	@Column(name = "au_remark", length = 200)
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public FileMD5 getMd5() {
		return md5;
	}

	public void setMd5(FileMD5 md5) {
		this.md5 = md5;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
