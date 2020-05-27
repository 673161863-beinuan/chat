package com.beinuan.chat;

import java.io.Serializable;

/**
 * @Time 2020��5��8��
 * @Author Zhao Minglei
 *
 *
 *         ������Ϣ��װ��
 */
public class Chat implements Serializable {

	private static final long serialVersionUID = 4058485121419391969L;
	/**
	 * �������û���
	 */
	public String chatUser;
	/**
	 * ��������
	 */
	public String chatMessage;
	/**
	 * ���ܶ����û���
	 */
	public String chatToUser;
	/**
	 * �Ƿ�˽��
	 */
	public boolean whisper;

}