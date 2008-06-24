package com.irc;

/*
 * 全体で利用される情報を
 * staticに保持しておくクラス
 * 
 * author: deepneko
 */
public class IRCSystem {

	/*
	 * 全般の設定
	 */
	public static int CLIENT_MODE = 1;
	
	public static int SERVER_MODE = 2;
	
	public static Version version = new Version(0, 3, 1);
	
	/*
	 * 多重起動禁止で利用するポート
	 */
	public static int BIND_PORT = 60000;
	
	/*
	 * GUI関連
	 */
	public static boolean isGUI = false;
	
	/*
	 * サーバ関連
	 */
	public static int BROADCAST_PORT = 50000;
	
	public static String BROADCAST_SERVER;
	
	public static String CLIENT_LIST = "ClientList.txt";
	
	/*
	 * クライアント関連
	 */
	public static int MESSAGE_SERVER_PORT = 40000;

	/*
	 * コマンド関連
	 */
	public static String COMMAND_EXIT = "@exit";
	
	public static String COMMAND_WHO = "@who";

	public static String COMMAND_WHOIS = "@whois";

	public static String COMMAND_VERSION = "@version";
}
