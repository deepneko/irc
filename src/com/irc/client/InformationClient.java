package com.irc.client;

import java.io.IOException;
import java.net.InetAddress;

import com.irc.IRCSystem;


/*
 * サーバとの接続を確認するクラス
 * 
 * author: deepneko
 */
public class InformationClient extends Client{

	private final static int ID = 0;
	
	private final static byte OK = 0;
	private final static byte NG = 1;
	
	public boolean ping(String server, int port) throws IOException{
		InetAddress addr = InetAddress.getByName(server);
		System.out.println(addr.getHostAddress());
		System.out.println(port);
		this.init(addr, port);
		
		this.sock.setSoTimeout(1000);
		
		w.writeByte(OK);
		w.flush();

		try{
			byte ack = r.readByte();
			if(ack == OK){
				w.writeByte(OK);
				w.flush();
				IRCSystem.BROADCAST_SERVER = sock.getInetAddress().getHostAddress();
				return true;
			}
		}catch(IOException e){
			sock.close();
		}
		
		return false;
	}
	
	@Override
	protected int getServiceID() {
		return ID;
	}
}