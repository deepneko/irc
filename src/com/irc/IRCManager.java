package com.irc;

import java.net.InetAddress;

/*
 * 管理クラスです
 * まだ何も使ってません
 * 
 * author: deepneko
 */
public class IRCManager {

	private String userID;
	
	private InetAddress serverAddr;
	
	private int serverPort;
	
	public IRCManager(String id, InetAddress addr, int port){
		this.userID = id;
		this.serverAddr = addr;
		this.serverPort = port;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public InetAddress getServerAddr(){
		return serverAddr;
	}
	
	public int getServerPort(){
		return serverPort;
	}
}
