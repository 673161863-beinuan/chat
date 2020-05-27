package com.beinuan.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @Time 2020��5��8��
 * @Author Zhao Minglei
 *
 *
 *
 *         �����Ҵ���ҳ��
 */
public class ChatRoom extends Thread implements ActionListener {
	static JFrame frmChat;

	JPanel pnlChat;

	JButton btnCls, btnExit, btnSend, btnClear, btnSave, btnPic,btnFile;

	JLabel lblUserList, lblUserMessage, lblSendMessage, lblChatUser;

	JLabel lblUserTotal, lblCount, lblBack;

	JTextField txtMessage;

	java.awt.List lstUserList;

	TextArea taUserMessage;

	JComboBox cmbUser;

	JCheckBox chPrivateChat;

	String strServerIp, strLoginName;

	Thread thread;

	final JLabel headLabel = new JLabel();

	Dimension scrnsize;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Message messobj = null;

	String serverMessage = "";

	// ���췽��
	public ChatRoom(String name, String ip) {
		strServerIp = ip;
		strLoginName = name;
		frmChat = new JFrame("������" + "[�û�:" + name + "]");
		pnlChat = new JPanel();
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().add(pnlChat);

		Font fntDisp1 = new Font("����", Font.PLAIN, 12);

		String list[] = { "������" };
		btnCls = new JButton("����");
		btnExit = new JButton("�˳�");
		btnSend = new JButton("����");
		btnSave = new JButton("�����¼");
		btnPic = new JButton("ͼƬ");
		btnFile = new JButton("�ļ�");
		lblUserList = new JLabel("�������û��б�");
		lblUserMessage = new JLabel("��������Ϣ��");
		lblSendMessage = new JLabel("��������:");
		lblChatUser = new JLabel("���:");
		lblUserTotal = new JLabel("��������:");
		lblCount = new JLabel("0");
		lstUserList = new java.awt.List();
		txtMessage = new JTextField(170);
		cmbUser = new JComboBox(list);
		cmbUser.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				freshHead();
			}
		});
		chPrivateChat = new JCheckBox("˽��");
		taUserMessage = new TextArea("", 300, 200, TextArea.SCROLLBARS_VERTICAL_ONLY);// ֻ�����¹���
		taUserMessage.setForeground(new Color(0, 0, 0));
		taUserMessage.setEditable(false); // ����д��

		pnlChat.setLayout(null);
		pnlChat.setBackground(new Color(52, 130, 203));
		btnSave.setBounds(500, 330, 82, 25);
		btnCls.setBounds(400, 330, 80, 25);
		btnFile.setBounds(320, 360, 80, 25);
		// ����ѡ��ͼƬ��ť
		btnPic.setBounds(400, 360, 80, 25);
		btnExit.setBounds(500, 360, 82, 25);
		btnSend.setBounds(500, 300, 82, 25);

		lblUserList.setBounds(5, 0, 120, 40);
		lblUserTotal.setBounds(130, 0, 60, 40);
		lblCount.setBounds(190, 0, 60, 40);
		lblUserMessage.setBounds(225, 0, 180, 40);
		lblChatUser.setBounds(10, 290, 40, 40);
		lblSendMessage.setBounds(210, 290, 60, 40);

		lstUserList.setBounds(5, 40, 210, 255);
		taUserMessage.setBounds(225, 40, 360, 255);
		txtMessage.setBounds(270, 300, 210, 25);
		cmbUser.setBounds(50, 300, 80, 25);
		chPrivateChat.setBounds(333, 336, 60, 20);
		btnCls.setFont(fntDisp1);
		btnExit.setFont(fntDisp1);
		btnSend.setFont(fntDisp1);
		btnSave.setFont(fntDisp1);
		btnPic.setFont(fntDisp1);
		btnFile.setFont(fntDisp1);
		lblUserList.setFont(fntDisp1);
		lblUserMessage.setFont(fntDisp1);
		lblChatUser.setFont(fntDisp1);
		lblSendMessage.setFont(fntDisp1);
		lblUserTotal.setFont(fntDisp1);
		lblCount.setFont(fntDisp1);
		cmbUser.setFont(fntDisp1);
		chPrivateChat.setFont(fntDisp1);

		lblUserList.setForeground(Color.YELLOW);
		lblUserMessage.setForeground(Color.YELLOW);
		lblSendMessage.setForeground(Color.black);
		lblChatUser.setForeground(Color.black);
		lblSendMessage.setForeground(Color.black);
		lblUserTotal.setForeground(Color.YELLOW);
		lblCount.setForeground(Color.YELLOW);
		cmbUser.setForeground(Color.black);
		chPrivateChat.setForeground(Color.black);
		lstUserList.setBackground(Color.white);
		taUserMessage.setBackground(Color.white);
		btnCls.setBackground(Color.ORANGE);
		btnExit.setBackground(Color.ORANGE);
		btnSend.setBackground(Color.PINK);
		btnSave.setBackground(Color.ORANGE);
		btnPic.setBackground(Color.ORANGE);
		btnFile.setBackground(Color.ORANGE);
		pnlChat.add(btnCls);
		pnlChat.add(btnExit);
		pnlChat.add(btnSend);
		pnlChat.add(btnSave);
		pnlChat.add(btnPic);
		pnlChat.add(btnFile);
		pnlChat.add(lblUserList);
		pnlChat.add(lblUserMessage);
		pnlChat.add(lblSendMessage);
		pnlChat.add(lblChatUser);
		pnlChat.add(lblUserTotal);
		pnlChat.add(lblCount);
		pnlChat.add(lstUserList);
		pnlChat.add(taUserMessage);
		pnlChat.add(txtMessage);
		pnlChat.add(cmbUser);
		pnlChat.add(chPrivateChat);

		frmChat.addWindowListener(new Windowclose());
		btnCls.addActionListener(this);
		btnExit.addActionListener(this);
		btnSend.addActionListener(this);
		btnSave.addActionListener(this);
		btnPic.addActionListener(this);
		btnFile.addActionListener(this);
		lstUserList.addActionListener(this);
		txtMessage.addActionListener(this);

		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setIcon(new ImageIcon("face//1.JPG"));
		headLabel.setBounds(15, 335, 70, 60);
		pnlChat.add(headLabel);

		// ��������ҳ����Ϣˢ���߳�
		Thread thread = new Thread(this);
		thread.start();

		frmChat.setSize(600, 420);
		frmChat.setVisible(true);
		frmChat.setResizable(false);

		// �����ڶ�λ����Ļ����
		scrnsize = toolkit.getScreenSize();
		frmChat.setLocation(scrnsize.width / 2 - frmChat.getWidth() / 2, scrnsize.height / 2 - frmChat.getHeight() / 2);
		Image img = toolkit.getImage("images\\appico.jpg");
		frmChat.setIconImage(img);

	} // ���췽������

	@SuppressWarnings("deprecation")
	public void run() {
		int intMessageCounter = 0;
		int intUserTotal = 0;
		boolean isFirstLogin = true; // �ж��Ƿ�յ�½
		boolean isFound; // �ж��Ƿ��ҵ��û�
		Vector user_exit = new Vector();

		try {
			for (;;) {
				Socket toServer;
				toServer = new Socket(strServerIp, 1001);
				// ����Ϣ����������
				messobj = new Message();
				ObjectOutputStream streamtoserver = new ObjectOutputStream(toServer.getOutputStream());
				streamtoserver.writeObject((Message) messobj);
				// �����Է���������Ϣ
				ObjectInputStream streamfromserver = new ObjectInputStream(toServer.getInputStream());
				messobj = (Message) streamfromserver.readObject();
				// //////ˢ��������Ϣ�б�//////////
				if (isFirstLogin) // ����յ�½
				{
					intMessageCounter = messobj.chat.size(); // ���θ��û���½ǰ����������
					isFirstLogin = false;
				}
				if (!serverMessage.equals(messobj.serverMessage)) {
					serverMessage = messobj.serverMessage;
					taUserMessage.append("[ϵͳ��Ϣ]��" + serverMessage + "\n");
				}
				for (int i = intMessageCounter; i < messobj.chat.size(); i++) {
					Chat temp = (Chat) messobj.chat.elementAt(i);

					String temp_message;
					if (temp.chatUser.equals(strLoginName)) {
						if (temp.chatToUser.equals(strLoginName)) {
							temp_message = "ϵͳ��ʾ�����벻Ҫ�������" + "\n";
						} else {
							// �������Ļ�
							if (!temp.whisper) 
							{
								temp_message = "���㡿�ԡ�" + temp.chatToUser + "��" + "˵��" + temp.chatMessage + "\n";
							} else {
								temp_message = "���㡿���Ķԡ�" + temp.chatToUser + "��" + "˵��" + temp.chatMessage + "\n";
							}
						}
					} else {
						if (temp.chatToUser.equals(strLoginName)) {
							if (!temp.whisper) 
							{
								temp_message = "��" + temp.chatUser + "���ԡ��㡿" + "˵��" + temp.chatMessage + "\n";
							} else {
								temp_message = "��" + temp.chatUser + "�����Ķԡ��㡿" + "˵��" + temp.chatMessage+ "\n";
							}
						} else {
							// �Է�û����������
							if (!temp.chatUser.equals(temp.chatToUser)) 
							{
								if (!temp.whisper) // �������Ļ�
								{
									temp_message = "��" + temp.chatUser + "���ԡ�" + temp.chatToUser + "��" + "˵��" + temp.chatMessage + "\n";
								} else {
									temp_message = "";
								}
							} else {
								temp_message = "";
							}
						}
					}
					taUserMessage.append(temp_message);
					intMessageCounter++;
				}

				// //////ˢ�������û�//////////
				lstUserList.clear();
				for (int i = 0; i < messobj.userOnLine.size(); i++) {
					String User = ((Customer) messobj.userOnLine.elementAt(i)).custName;
					lstUserList.addItem(User);
				}
				Integer a = new Integer(messobj.userOnLine.size());
				lblCount.setText(a.toString());
				// ��ʾ�û����������ҵ���Ϣ
				if (messobj.userOnLine.size() > intUserTotal) {
					String tempstr = ((Customer) messobj.userOnLine.elementAt(messobj.userOnLine.size() - 1)).custName;
					if (!tempstr.equals(strLoginName)) {
						taUserMessage.append("��" + tempstr + "������" + "\n");
					}
				}
				if (messobj.userOnLine.size() < intUserTotal) {
					for (int b = 0; b < user_exit.size(); b++) {
						isFound = false;
						for (int c = 0; c < messobj.userOnLine.size(); c++) {
							String tempstr = ((Customer) user_exit.elementAt(b)).custName;

							if (tempstr.equals(((Customer) messobj.userOnLine.elementAt(c)).custName)) {
								isFound = true;
								break;
							}
						}
						if (!isFound) // û�з��ָ��û�
						{
							String tempstr = ((Customer) user_exit.elementAt(b)).custName;

							if (!tempstr.equals(strLoginName)) {
								taUserMessage.append("��" + tempstr + "������" + "\n");
							}
						}
					}
				}
				user_exit = messobj.userOnLine;
				intUserTotal = messobj.userOnLine.size();
				streamtoserver.close();
				streamfromserver.close();
				toServer.close();
				Thread.sleep(3000);
			}

		} catch (Exception e) {
			@SuppressWarnings("unused")
			JOptionPane jop = new JOptionPane();
			JOptionPane.showMessageDialog(null, "�������ӷ�������");
			e.printStackTrace();
			frmChat.dispose();
		}

	} // run()����

	private void exitChatRoom() {
		exit();
	}

	// /////////������ť��Ӧ//////////////
	public void actionPerformed(ActionEvent ae) {
		Object source = (Object) ae.getSource();
		if (source.equals(btnCls)) {
			clearMessage();
		}
		if (source.equals(btnExit)) {
			exit();
		}
		if (source.equals(btnSend)) {
			sendMessage();
		}
		if (source.equals(btnSave)) {
			saveMessage();
		}
		if (source.equals(btnPic)) {
			selectPic();
		}
		if (source.equals(btnFile)) {
			selectPic();
		}
		if (source.equals(lstUserList)) // ˫���б��
		{
			changeUser();
		}
	} // actionPerformed()����

	// /////////�������ڹر���Ӧ//////////////
	class Windowclose extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit();
		}
	}

	// "����"��ť
	public void clearMessage() {
		taUserMessage.setText("");
	}

	// "�˳�"��ť
	public void exit() {
		Exit exit = new Exit();
		exit.exitname = strLoginName;
		// �����˳���Ϣ
		try {
			Socket toServer = new Socket(strServerIp, 1001);
			// �������������Ϣ
			ObjectOutputStream outObj = new ObjectOutputStream(toServer.getOutputStream());
			outObj.writeObject(exit);
			outObj.close();
			toServer.close();

			frmChat.dispose();
		} catch (Exception e) {
		}

	} // exit()����

	// "����"��ť
	public void sendMessage() {
		Chat chatobj = new Chat();
		chatobj.chatUser = strLoginName;
		chatobj.chatMessage = txtMessage.getText();
		chatobj.chatToUser = String.valueOf(cmbUser.getSelectedItem());
		
		chatobj.whisper = chPrivateChat.isSelected() ? true : false;
		try {
			Socket toServer = new Socket(strServerIp, 1001);
			ObjectOutputStream outObj = new ObjectOutputStream(toServer.getOutputStream());
			outObj.writeObject(chatobj);
			txtMessage.setText(""); // ����ı���
			outObj.close();
			toServer.close();
		} catch (Exception e) {
		}
	}

	// "����"��ť
	public void saveMessage() {
		try {
			// �˴��޸�Ϊ�Զ��������������¼�ļ�
			FileOutputStream fileoutput = new FileOutputStream(
					new File("config\\" + this.strLoginName + "_message.txt"), true);
			String temp = taUserMessage.getText();
			fileoutput.write(temp.getBytes());
			fileoutput.close();
			JOptionPane.showMessageDialog(null, "�����¼������" + "config\\" + this.strLoginName + "_message.txt");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// ����ѡ��ͼƬ����
	@SuppressWarnings("resource")
	public void selectPic() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpg", "jpg");
		JFileChooser fc = new JFileChooser();
		// ����һ���ļ�ɸѡ��
		fc.setFileFilter(filter);
		// ���ò������ѡ
		fc.setMultiSelectionEnabled(false);
		// ʹ��showOpenDialog()��������ʾ����ѡ���ļ��Ĵ��ڣ���ѡ����ĳ���ļ��󣬻��߹رմ˴�����ô���᷵��һ��
		// ������ֵ��������ص���0�������Ѿ�ѡ����ĳ���ļ����������1����ѡ����ȡ����ť����ֱ�ӹر��˴���
		int result = fc.showSaveDialog(null);
		System.out.println(result);
		// JFileChooser.APPROVE_OPTION�Ǹ����ͳ���������0������˵������0��ֵ���ǲ�ִ����ز���������ʲôҲ����
		if (result == JFileChooser.APPROVE_OPTION) {
			// ��ȡ��ǰѡ����ļ�·��
			File file = fc.getSelectedFile();
			// �ж�����ļ��Ƿ���.sql��β��������Ƿ�����ʾ
			if (!file.getPath().endsWith(".jpg")) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ĳ���jpg��ʽͼƬ��");
			}
			// �����ļ�·��
			String path = file.getPath();
			Chat chatobj = new Chat();
			chatobj.chatUser = strLoginName;
			try {
				FileInputStream fs = new FileInputStream(path);
				int len = 0;
				byte[] bytes = new byte[1024];
				while ((len = fs.read(bytes)) != -1) { // 3����������ÿ��bytes��С��ȡ
					String picContent = new String(bytes, 0, len);
					chatobj.chatMessage = picContent;
					chatobj.chatToUser = String.valueOf(cmbUser.getSelectedItem());
					chatobj.whisper = chPrivateChat.isSelected() ? true : false;
					try {
						Socket toServer = new Socket(strServerIp, 1001);
						ObjectOutputStream outObj = new ObjectOutputStream(toServer.getOutputStream());
						outObj.writeObject(chatobj);
						txtMessage.setText(""); // ����ı���
						outObj.close();
						toServer.close();
					} catch (Exception e) {
					}

				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	// ����ѡ�û���ӵ�cmbUser��
	public void changeUser() {

		boolean key = true;
		String selected = lstUserList.getSelectedItem();
		for (int i = 0; i < cmbUser.getItemCount(); i++) {
			if (selected.equals(cmbUser.getItemAt(i))) {
				key = false;
				break;
			}
		}
		if (key == true) {
			cmbUser.insertItemAt(selected, 0);
		}
		String head = getUserHead(lstUserList.getSelectedItem());
		cmbUser.setSelectedItem(selected);

		headLabel.setIcon(new ImageIcon("face//" + head + ".JPG"));
	}

	protected void freshHead() {
		String head = getUserHead(cmbUser.getSelectedItem().toString());
		headLabel.setIcon(new ImageIcon("face//" + head + ".JPG"));
	}

	private String getUserHead(String selectedItem) {
		String head = "oo";
		for (int i = 0; i < messobj.userOnLine.size(); i++) {
			String User = ((Customer) messobj.userOnLine.elementAt(i)).custName;
			head = ((Customer) messobj.userOnLine.elementAt(i)).custHead;
			if (User.equals(selectedItem)) {
				break;
			}
		}
		return head;
	}

	public static void main(String args[]) {
		new ChatRoom("�����û�", "127.0.0.1");
	}

}