package com.irc.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import com.irc.IRCSystem;
import com.irc.io.ByteConverter;
import com.irc.io.RAWWriter;
import com.irc.io.Writer;

/*
 * サーバから接続している全クライアントに
 * メッセージをブロードキャストするクラス
 * 
 * ユーザとの接続が確立できなかった場合
 * ログアウト処理を行います
 * 
 * author: deepneko
 */
public class Broadcaster implements Runnable{

	String userID;
	String message;
	
	Socket sock;
	Writer w;
	
	public Broadcaster(String userID, String message){
		this.userID = userID;
		this.message = message;
		init();
	}
	
	public void init(){
		if(message.equals(IRCSystem.COMMAND_WHO))
		{
			this.message = ClientList.getLoginNameList();
		}
		else if(message.equals(IRCSystem.COMMAND_VERSION))
		{
			this.message = "Server version is " + IRCSystem.version.getVersion();
		}
		else if(message.indexOf(IRCSystem.COMMAND_WHOIS) == 0){
			String whoisUser = message.substring(IRCSystem.COMMAND_WHOIS.length()).trim();
			String whoisId = "";
			System.out.println(whoisUser);
			if(ClientList.isUser(whoisUser)){
				whoisId = ClientList.getMachineid(whoisUser);
				if(!whoisId.equals(""))
					this.message = whoisUser + " is " + whoisId;
			}else{
				this.message = whoisUser + " is not exist.";
			}
		}
	}
	
	public void start() throws IOException{
		Thread th = new Thread(this);
		th.start();
	}

	public void run() {
		Map<String, InetAddress> m = ClientList.loginList;
		Set<String> s = ClientList.loginList.keySet();
		Iterator<String> it = s.iterator();
		String id = "";
		while(it.hasNext()){
			try {
				id = (String)it.next();
				sock = new Socket(m.get(id), IRCSystem.MESSAGE_SERVER_PORT);
				w = new RAWWriter(sock.getOutputStream());

				w.writeBytes(ByteConverter.intTobytes(userID.length()));
				w.writeBytes(userID.getBytes());
				w.writeBytes(ByteConverter.intTobytes(message.getBytes().length));
				w.writeBytes(message.getBytes());
				w.flush();
			} catch (Exception e) {
				System.out.println("User is missing.");
				if(ClientList.logout(id))
					new Broadcaster("Server message", id + " logout.");
			}
		}
		
		try {
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException{
		if(sock != null){
			sock.close();
			sock = null;
		}
		if(w != null){
			w.close();
			w = null;
		}
	}
}