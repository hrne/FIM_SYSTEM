package com.modle.entity;

import java.util.List;

public class User {
	// <sf:hidden path="id" />
	private Integer id;

	// <sf:input path="name" />
	private String name;

	// <sf:input path="email" />
	private String email;

	// <sf:textarea path="address" />
	private String address;

	// <sf:password path="password" />
	private String password;

	// <sf:password path="confirmPassword" />
	private String confirmPassword;

	// <sf:checkbox path="newsletter" />
	private boolean newsletter;

	// <sf:checkboxes path="framework" />
	private List<String> framework;

	// <sf:radiobutton path="sex" />
	private String sex;

	// <sf:radiobuttons path="height" />
	private Integer height;

	// <sf:select path="country" /> + <sf:options items="${?}" />
	private String country;

	// <sf:select path="skill" items="${?}" multiple="true" />
	private List<String> skill;

	public boolean isNew() {
		return (this.id == null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public List<String> getFramework() {
		return framework;
	}

	public void setFramework(List<String> framework) {
		this.framework = framework;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getSkill() {
		return skill;
	}

	public void setSkill(List<String> skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", newsletter=" + newsletter + ", framework="
				+ framework + ", sex=" + sex + ", height=" + height + ", country=" + country + ", skill=" + skill + "]"
				+ isNew();
	}

}
