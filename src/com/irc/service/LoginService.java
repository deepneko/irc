package com.irc.service;

import java.io.IOException;
import java.net.SocketException;

import com.irc.io.ByteConverter;
import com.irc.server.Broadcaster;
import com.irc.server.ClientList;

/*
 * クライアントのログイン処理を行うクラス
 * 
 * author: deepneko
 */
public class LoginService extends Service {

	private final static int ID = 10;

	private final static byte OK = 0;

	private final static byte NG = 1;

	public void run() {
		try {
			this.sock.setSoTimeout(20000);

			byte[] id_length = new byte[4];
			byte[] id;
			byte[] pass_length = new byte[4];
			byte[] pass;
			byte[] machineid_length = new byte[4];
			byte[] machineid;

			r.readBytes(id_length);
			int length = ByteConverter.bytesToInt(id_length);
			id = new byte[length];
			r.readBytes(id);

			String user_id = new String(id).trim();
			System.out.println("user: " + user_id);

			r.readBytes(pass_length);
			length = ByteConverter.bytesToInt(pass_length);
			pass = new byte[length];
			r.readBytes(pass);

			String user_pass = new String(pass).trim();
			System.out.println("pass: " + user_pass);

			r.readBytes(machineid_length);
			length = ByteConverter.bytesToInt(machineid_length);
			machineid = new byte[length];
			r.readBytes(machineid);

			String machine_id = new String(machineid).trim();
			System.out.println("machineid: " + machine_id);

			if (ClientList.auth(user_id, user_pass)) {
				user_id = ClientList.login(user_id, sock.getInetAddress(),
						machine_id);
				w.writeBytes(ByteConverter.intTobytes(user_id.length()));
				w.writeBytes(user_id.getBytes());
			} else {
				w.writeBytes(ByteConverter.intTobytes(0));
				w.writeBytes("".getBytes());
			}
			w.flush();

			byte ack = r.readByte();
			if (ack == OK) {
				System.out.println("login success.");
				new Broadcaster("Server message", user_id + " has logged in.")
						.start();
			} else {
				System.out.println("login failed.");
			}
		} catch (SocketException e) {
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