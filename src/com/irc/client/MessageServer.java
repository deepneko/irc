package com.irc.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import com.irc.IRCManager;
import com.irc.IRCSystem;
import com.irc.io.ByteConverter;
import com.irc.io.RAWReader;
import com.irc.io.Reader;
import com.irc.ui.ClientVisualizer;

/*
 * サーバから受け取ったメッセージを受け取るクラス
 * 
 * author: deepneko
 */
public class MessageServer implements Runnable{

	private ClientVisualizer visualizer;
	private ServerSocket serverSock;
	private Reader r;

	public MessageServer(IRCManager manager){
	}

	public MessageServer(IRCManager manager, ClientVisualizer visualizer){
		this.visualizer = visualizer;
	}

	public void start() throws IOException{
		serverSock = new ServerSocket(IRCSystem.MESSAGE_SERVER_PORT);
		Thread th = new Thread(this);
		th.start();
	}
	
	public void close() throws IOException{
		if(serverSock != null){
			serverSock.close();
			serverSock = null;
		}
	}

	public void run() {
		byte[] id_length = new byte[4];
		byte[] id;
		byte[] message_length = new byte[4];
		byte[] message;
		int length;
		Object obj = new Object();

		try{
			while(serverSock != null){
				Socket sock = serverSock.accept();

				try {
					synchronized(obj){
						sock.setSoTimeout(1000);
						r = new RAWReader(sock.getInputStream());
	
						r.readBytes(id_length);
						length = ByteConverter.bytesToInt(id_length);
						id = new byte[length];
						r.readBytes(id);
	
						r.readBytes(message_length);
						length = ByteConverter.bytesToInt(message_length);
						message = new byte[length];
						r.readBytes(message);
	
						visualizer.receive(" [" + new String(id) + "]: " + new String(message));
						
						System.out.println("receive:" + new String(id) + ":" + new String(message));
						
						r.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
					if(sock != null){
						sock.close();
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}