package com.beinuan.chat;
/**
  *@Time  2020年5月8日
  *@Author Zhao Minglei
  *
  *
  * 用户注册信息封装类
  */
public class Register_Customer extends Object implements java.io.Serializable
{
	
	/**
	 * 随机生成序列号
	 */
	private static final long serialVersionUID = -3890143642337321758L;
	
	
	/**
	 * 姓名
	 */
	public String custName;
	/**
	 * 密码
	 */
	public String custPassword;
	/**
	 * 年龄
	 */	
	public String age;
	/**
	 * 性别
	 */	
	public String sex;
	/**
	 * 电子邮件
	 */
	public String email;
	 /**
	 * 头像文件名
	 */
	public String head;
	
}
