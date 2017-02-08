package com.ixiaofan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cs_account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 用户名
	 */
	@Column(name = "ac_username", length = 20)
	private String username;
	/**
	 * 密码
	 */
	@Column(name = "ac_password", length = 50)
	private String password;
	/**
	 * 邮箱
	 */
	@Column(name = "ac_email", length = 50)
	private String email;
	/**
	 * 邮箱激活状态
	 * 0为激活，1为未激活
	 */
	@Column(name = "ac_status", length = 20)
	private int status;
	/**
	 * 手机号
	 */
	@Column(name = "ac_phone", length = 11)
	private String phone;
	/**
	 * 身份证号
	 */
	@Column(name = "ac_card", length = 18)
	private String card;
	/**
	 * 真实姓名
	 */
	@Column(name = "ac_name", length = 20)
	private String name;
	/**
	 * 性别
	 */
	@Column(name = "ac_sex", length = 20)
	private String sex;
	/**
	 * 居住地址
	 */
	@Column(name = "ac_address", length = 50)
	private String address;
	/**
	 * 用户级别
	 */
	@Column(name = "ac_userlv", length = 5)
	private int userlv;
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ac_creattime", length = 50)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creattime;
	
	@Column(name="ac_validatecode",length = 20)
	private String validatecode;
	/**
	 * 验证码
	 */
	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUserlv() {
		return userlv;
	}

	public void setUserlv(int userlv) {
		this.userlv = userlv;
	}

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

}
