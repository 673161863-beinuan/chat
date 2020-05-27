package com.beinuan.chat;
/**
  *@Time  2020年5月8日
  *@Author Zhao Minglei
  *
  * 用户信息封装类
  */
public class Customer extends Object implements java.io.Serializable
{
	
	private static final long serialVersionUID = -9215977405584592618L;
	/**
	 * 用户名
	 */
	public String custName;
	/**
	 * 密码
	 */
	public String custPassword;
	/**
	 * 用户头像
	 */
	public String custHead;
}
