package com.irc.service;

import java.io.IOException;
import java.net.SocketException;

/*
 * クライアントからの接続確認を行うクラス
 * 
 * author: deepneko
 */
public class InformationService extends Service{

	private final static int ID = 0;
	
	private final static byte OK = 0;
	private final static byte NG = 1;
	
	public void run(){
		try {
			this.sock.setSoTimeout(20000);
			byte ack = r.readByte();
			
			if(ack == OK){
				w.writeByte(OK);
				w.flush();
				System.out.println("ping ok.");
			}else{
				w.writeByte(NG);
				w.flush();
				System.out.println("ping failed.");
			}
		} catch(SocketException e){
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	@Override
	public int getServiceID() {
		return ID;
	}
}