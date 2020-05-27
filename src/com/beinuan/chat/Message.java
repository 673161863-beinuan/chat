package com.beinuan.chat;

import java.io.Serializable;
import java.util.Vector;

/**
 * @Time 2020��5��8��
 * @Author Zhao Minglei
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1375425751109742151L;

	/**
	 * �û����߶���
	 */
	public Vector userOnLine;

	/**
	 * ������Ϣ��
	 */
	public Vector chat;

	/**
	 * ����
	 */
	public String serverMessage;

}
