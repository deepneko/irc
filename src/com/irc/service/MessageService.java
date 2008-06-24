package com.irc.service;

import java.io.IOException;
import java.net.SocketException;

import com.irc.io.ByteConverter;
import com.irc.server.Broadcaster;
import com.irc.server.ClientList;

/*
 * クライアントからメッセージを受け取ったら
 * 接続している全クライアントにメッセージを送るクラス
 * 
 * author: deepneko
 */
public class MessageService extends Service{

	private final static int ID = 20;
	
	private final static byte OK = 0;
	private final static byte NG = 1;

	public void run(){
		try {
			this.sock.setSoTimeout(20000);
			
			byte[] id_length = new byte[4];
			byte[] id;
			byte[] message_length = new byte[4];
			byte[] message;

			r.readBytes(id_length);
			int length = ByteConverter.bytesToInt(id_length);
			id = new byte[length];
			r.readBytes(id);

			String user_id = new String(id).trim();
			System.out.println("user: " + user_id);

			r.readBytes(message_length);
			length = ByteConverter.bytesToInt(message_length);
			message = new byte[length];
			r.readBytes(message);
			
			String user_message = new String(message).trim();
			System.out.println("message: " + user_message);

			if(ClientList.isUser(user_id)){
				w.writeByte(OK);
			}else{
				w.writeByte(NG);
			}
			w.flush();

			byte ack = r.readByte();
			
			if(ack == OK){
				System.out.println("message received, then message broadcast.");
				new Broadcaster(user_id, user_message).start();
			}else{
				System.out.println("message unreached.");
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