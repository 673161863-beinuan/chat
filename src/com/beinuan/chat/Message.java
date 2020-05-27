package com.beinuan.chat;

import java.io.Serializable;
import java.util.Vector;

/**
 * @Time 2020年5月8日
 * @Author Zhao Minglei
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1375425751109742151L;

	/**
	 * 用户在线对象集
	 */
	public Vector userOnLine;

	/**
	 * 聊天信息集
	 */
	public Vector chat;

	/**
	 * 公告
	 */
	public String serverMessage;

}
