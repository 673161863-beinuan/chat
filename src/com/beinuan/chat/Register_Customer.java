package com.beinuan.chat;

/**
 * @Time 2020��5��8��
 * @Author Zhao Minglei
 *
 *
 *         �û�ע����Ϣ��װ��
 */
public class Register_Customer extends Object implements java.io.Serializable {

	/**
	 * ����������к�
	 */
	private static final long serialVersionUID = -3890143642337321758L;

	/**
	 * ����
	 */
	private String username;
	/**
	 * ����
	 */
	private String password;
	/**
	 * ����
	 */
	private String age;
	/**
	 * �Ա�
	 */
	private Integer gender;
	/**
	 * �����ʼ�
	 */
	public String email;

	/**
	 * ͷ���ļ���
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
