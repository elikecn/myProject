package com.jspxcms.abc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jspxcms.core.domain.Site;

@Entity
@Table(name="abc_demo")
public class Demo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 男
	 */
	public static final String MALE = "M";
	/**
	 * 女
	 */
	public static final String FEMALE = "F";
	
	/**
	 * id
	 */
	@Id
	@Column(name = "f_demo_id", unique = true, nullable = false)
	@TableGenerator(name = "tg_abc_demo", pkColumnValue = "abc_demo", table = "t_id_table", pkColumnName = "f_table", valueColumnName = "f_id_value", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_abc_demo")
	private Integer id;
	
	/**
	 * 站点
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_site_id", nullable = false)
	private Site site;
	
	/**
	 * 姓名
	 */
	@NotBlank
	@Column(name = "f_name", nullable = false, length = 100)
	private String name;
	
	/**
	 * 性别
	 */
	@NotBlank
	@Pattern(regexp = "['M','F']")
	@Column(name = "f_sex", nullable = false, length = 1)
	private String sex;
	
	/**
	 * 生日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_birth_date", nullable = false, length = 19)
	private Date birthDate;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_create_date", nullable = false, length = 19)
	private Date createDate;
	
	/**
	 * 邮箱
	 */
	@Column(name = "f_email", length = 100)
	private String email;
	
	/**
	 * 头像
	 */
	@Column(name = "f_icon", length = 500)
	private String icon;
	
	/**
	 * 备注
	 */
	/*@NotBlank*/
	@Column(name = "f_remark")
	private String remark;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Demo(Integer id, Site site, String name, String sex, Date birthDate, Date createDate, String email,
			String icon, String remark) {
		super();
		this.id = id;
		this.site = site;
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.createDate = createDate;
		this.email = email;
		this.icon = icon;
		this.remark = remark;
	}

	public Demo() {
		super();
	}
}
