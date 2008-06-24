package com.irc.client;

import java.io.IOException;


import com.irc.IRCManager;
import com.irc.IRCSystem;
import com.irc.io.ByteConverter;

/*
 * サーバにメッセージを送るクラス
 * 
 * author: deepneko
 */
public class MessageClient extends Client{

	private final static int ID = 20;
	
	private final static byte OK = 0;
	private final static byte NG = 1;
	
	private IRCManager manager;
	
	public MessageClient(IRCManager manager){
		this.manager = manager;
	}
	
	public synchronized boolean send(String message) throws IOException{
		this.init(manager.getServerAddr(), manager.getServerPort());
		
		String userID = manager.getUserID();
		w.writeBytes(ByteConverter.intTobytes(userID.length()));
		w.writeBytes(userID.getBytes());
		w.writeBytes(ByteConverter.intTobytes(message.getBytes().length));
		w.writeBytes(message.getBytes());
		w.flush();
		
		byte result = r.readByte();
		
		if(result != OK){
			w.writeByte(NG);
			w.flush();
			return false;
		}else{
			w.writeByte(OK);
			w.flush();
			if(message.equals(IRCSystem.COMMAND_EXIT))
				System.exit(1);
			return true;
		}
	}
	
	@Override
	protected int getServiceID() {
		return ID;
	}
}