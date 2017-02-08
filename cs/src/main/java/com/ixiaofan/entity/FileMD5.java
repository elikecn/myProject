package com.ixiaofan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cs_filemd5")
public class FileMD5 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * md5字符串
	 */
	@Column(name = "fi_stringmd5", length = 50)
	private String stringmd5;
	
	/**
	 * 文件后缀名
	 */
	@Column(name = "fi_postfix", length = 20)
	private String postfix;
	
	/**
	 * 文件上传后名称
	 */
	@Column(name = "fi_name", length = 20)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStringmd5() {
		return stringmd5;
	}

	public void setStringmd5(String stringmd5) {
		this.stringmd5 = stringmd5;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
