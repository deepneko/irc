package com.irc;

/*
 * �S�̂ŗ��p��������
 * static�ɕێ����Ă����N���X
 * 
 * author: deepneko
 */
public class IRCSystem {

	/*
	 * �S�ʂ̐ݒ�
	 */
	public static int CLIENT_MODE = 1;
	
	public static int SERVER_MODE = 2;
	
	public static Version version = new Version(0, 3, 1);
	
	/*
	 * ���d�N���֎~�ŗ��p����|�[�g
	 */
	public static int BIND_PORT = 60000;
	
	/*
	 * GUI�֘A
	 */
	public static boolean isGUI = false;
	
	/*
	 * �T�[�o�֘A
	 */
	public static int BROADCAST_PORT = 50000;
	
	public static String BROADCAST_SERVER;
	
	public static String CLIENT_LIST = "ClientList.txt";
	
	/*
	 * �N���C�A���g�֘A
	 */
	public static int MESSAGE_SERVER_PORT = 40000;

	/*
	 * �R�}���h�֘A
	 */
	public static String COMMAND_EXIT = "@exit";
	
	public static String COMMAND_WHO = "@who";

	public static String COMMAND_WHOIS = "@whois";

	public static String COMMAND_VERSION = "@version";
}
