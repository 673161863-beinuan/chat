package com.beinuan.chat;

/**
 * @Time 2020年5月8日
 * @Author Zhao Minglei
 *
 *
 *         用户注册信息封装类
 */
public class Register_Customer extends Object implements java.io.Serializable {

	/**
	 * 随机生成序列号
	 */
	private static final long serialVersionUID = -3890143642337321758L;

	/**
	 * 姓名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 电子邮件
	 */
	public String email;

	/**
	 * 头像文件名
	 */
	public String head;

	public Register_Customer() {
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Register_Customer [username=" + username + ", password=" + password + ", age=" + age + ", gender="
				+ gender + ", email=" + email + ", head=" + head + "]";
	}

}
