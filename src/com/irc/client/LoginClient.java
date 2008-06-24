package com.irc.client;

import java.io.IOException;
import java.net.InetAddress;

import com.irc.io.ByteConverter;

/*
 * ログイン処理を行うクラス
 * 
 * author: deepneko
 */
public class LoginClient extends Client{

	private final static int ID = 10;
	
	private final static byte OK = 0;
	private final static byte NG = 1;
	
	private String userID;
	private String userPass;
	private String machineName;
	
	public LoginClient(String userID, String userPass){
		this.userID = userID;
		this.userPass = userPass;
		this.machineName = System.getProperty("user.name");
	}
	
	public String login(String address, int port) throws IOException{
		InetAddress addr = InetAddress.getByName(address);
		this.init(addr, port);
		
		if(this.userID == null || this.userPass == null){
			return "";
		}
		
		w.writeBytes(ByteConverter.intTobytes(userID.length()));
		w.writeBytes(userID.getBytes());
		w.writeBytes(ByteConverter.intTobytes(userPass.length()));
		w.writeBytes(userPass.getBytes());
		w.writeBytes(ByteConverter.intTobytes(machineName.length()));
		w.writeBytes(machineName.getBytes());
		w.flush();

		byte[] id_length = new byte[4];
		byte[] id;
		r.readBytes(id_length);
		int length = ByteConverter.bytesToInt(id_length);
		id = new byte[length];
		r.readBytes(id);

		String user_id = new String(id).trim();
		System.out.println("user: " + user_id);
		
		if(!user_id.equals("") && user_id != null){
			w.writeByte(OK);
			w.flush();
			return user_id;
		}else{
			w.writeByte(NG);
			w.flush();
			return "";
		}
	}
	
	public String getUserID(){
		return userID;
	}
	
	public String getUserPass(){
		return userPass;
	}
	
	@Override
	protected int getServiceID() {
		return ID;
	}
}