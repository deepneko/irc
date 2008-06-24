package com.irc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import com.irc.client.InformationClient;
import com.irc.client.LoginClient;
import com.irc.client.MessageClient;
import com.irc.client.MessageServer;
import com.irc.server.ClientList;
import com.irc.server.Server;
import com.irc.service.InformationService;
import com.irc.service.LoginService;
import com.irc.service.MessageService;
import com.irc.ui.ClientVisualizer;
import com.irc.ui.ServerVisualizer;

/*
 * エントリーポイント
 * 
 * -s
 * サーバモードで起動
 * 
 * -c <user_ID> <password>
 * クライアントモードで起動
 * 
 * -cs <server_IP>
 * サーバのIPを指定する
 * 
 * -g
 * GUIをONにする
 * クライアントモードでこのオプションを指定しなかった場合,テスト接続をして終了する
 */
public class IRCMain {
	public static void main(String args[]){
		String str = "";
		String userID = "";
		String userPass = "";
		String serverIP = "";
		int mode = 0;

		/*
		 * 引数チェック
		 */
		for (int i = 0; i < args.length; i++) {
			str = args[i];
			if (str.equals("-s")) {
				mode = IRCSystem.SERVER_MODE;
			} else if (str.equals("-g")) {
				IRCSystem.isGUI = true;
			} else if (str.equals("-cs")) {
				serverIP = args[++i];
			} else if (str.equals("-c")) {
				mode = IRCSystem.CLIENT_MODE;
				userID = args[++i];
				userPass = args[++i];
			}
		}

		/*
		 * サーバモード
		 */
		if (mode == IRCSystem.SERVER_MODE) {
			Server server = new Server(IRCSystem.BROADCAST_PORT);
			boolean addServiceResult = false;

			addServiceResult = server.addService(LoginService.class);
			addServiceResult = server.addService(MessageService.class);
			addServiceResult = server.addService(InformationService.class);

			ClientList.init();
			ClientList.debug();

			if (addServiceResult) {
				try {
					server.start();
					if (IRCSystem.isGUI)
						new ServerVisualizer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * クライアントモード
		 */
		else if (mode == IRCSystem.CLIENT_MODE) {
			ServerSocket socket = null;
			try {
				socket = new ServerSocket(IRCSystem.BIND_PORT);
			} catch (IOException e) {
				socket = null;
			}
			if (socket == null) {
				System.out.println("");
				return;
			}

			try {
				InformationClient ic = new InformationClient();
				if (ic.ping(serverIP, IRCSystem.BROADCAST_PORT)) {
					System.out.println("Server is exist.");
				}

				/*
				 * ログイン処理
				 */
				LoginClient lc = new LoginClient(userID, userPass);
				userID = lc.login(serverIP, IRCSystem.BROADCAST_PORT);

				if (!userID.equals("") && userID != null) {
					System.out.println("login success.");

					IRCManager manager = new IRCManager(userID, InetAddress
							.getByName(IRCSystem.BROADCAST_SERVER),
							IRCSystem.BROADCAST_PORT);
					MessageClient mc = new MessageClient(manager);

					/*
					 * GUI ON
					 */
					if (IRCSystem.isGUI) {
						ClientVisualizer ui = new ClientVisualizer(mc);
						MessageServer ms = new MessageServer(manager, ui);
						ms.start();
					}

					/*
					 * GUI OFF
					 */
					else {
						MessageServer ms = new MessageServer(manager);
						ms.start();
						mc.send("テストです");
					}
				} else {
					System.out.println("login failed.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out
						.println("Connect failed. (Maybe, Server's IP is not wrong.)");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("There are not enough args.");
			}
		}
	}
}