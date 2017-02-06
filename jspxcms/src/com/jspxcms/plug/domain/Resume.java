package com.jspxcms.plug.domain;

import java.sql.Timestamp;
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
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import com.jspxcms.core.domain.Site;

@Entity
@Table(name = "plug_resume")
@ExcelTarget("resumeEntity")
public class Resume implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 男
	 */
	public static final String MALE = "M";
	/**
	 * 女
	 */
	public static final String FEMALE = "F";

	@Transient
	public void applyDefaultValue() {
		if (getCreationDate() == null) {
			setCreationDate(new Timestamp(System.currentTimeMillis()));
		}
		if (getGender() == null) {
			setGender(MALE);
		}
	}

	private Integer id;
	private Site site;
	@Excel(name = "姓名", orderNum = "1", needMerge = true)
	private String name;
	@Excel(name="应聘职位")
	private String post;
	@Excel(name="创建时间", databaseFormat="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	@Excel(name="性别", orderNum = "2" )
	private String gender;
	@Excel(name="生日")
	private Date birthDate;
	@Excel(name="手机号")
	private String mobile;
	@Excel(name="电子邮箱")
	private String email;
	private Integer expectedSalary;
	private String educationExperience;
	private String workExperience;
	@Excel(name="备注")
	private String remark;
	@Excel(name="头像", type=2, savePath="uploads")
	private String icon;
	
	
	
	public Resume() {
		super();
	}

	public Resume(Integer id, Site site, String name, String post,
			Date creationDate, String gender, Date birthDate, String mobile,
			String email, Integer expectedSalary, String educationExperience,
			String workExperience, String remark, String icon) {
		super();
		this.id = id;
		this.site = site;
		this.name = name;
		this.post = post;
		this.creationDate = creationDate;
		this.gender = gender;
		this.birthDate = birthDate;
		this.mobile = mobile;
		this.email = email;
		this.expectedSalary = expectedSalary;
		this.educationExperience = educationExperience;
		this.workExperience = workExperience;
		this.remark = remark;
		this.icon = icon;
	}

	@Id
	@Column(name = "f_resume_id", unique = true, nullable = false)
	@TableGenerator(name = "tg_plug_resume", pkColumnValue = "plug_resume", table = "t_id_table", pkColumnName = "f_table", valueColumnName = "f_id_value", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_plug_resume")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_site_id", nullable = false)
	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@NotBlank
	@Column(name = "f_name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@Column(name = "f_post", length = 100)
	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_creation_date", nullable = false, length = 19)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@NotBlank
	@Pattern(regexp = "['M','F']")
	@Column(name = "f_gender", nullable = false, length = 1)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_birth_date", nullable = false, length = 19)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "f_mobile", length = 100)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "f_email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "f_expected_salary")
	public Integer getExpectedSalary() {
		return this.expectedSalary;
	}

	public void setExpectedSalary(Integer expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	@Column(name = "f_education_experience")
	public String getEducationExperience() {
		return this.educationExperience;
	}

	public void setEducationExperience(String educationExperience) {
		this.educationExperience = educationExperience;
	}

	@Column(name = "f_work_experience")
	public String getWorkExperience() {
		return this.workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	@Column(name = "f_remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "f_icon", length = 500)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

}
