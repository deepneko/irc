package com.irc.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.irc.io.RAWReader;
import com.irc.io.RAWWriter;
import com.irc.io.Reader;
import com.irc.io.Writer;

/*
 * 各クライアントクラスの基底となるクラス
 * 
 * author: deepneko
 */
public abstract class Client {

	private InetAddress addr;
	private int port;
	
	protected Socket sock;
	protected Writer w;
	protected Reader r;
	
	public void init(InetAddress addr, int port) throws IOException{
		
		this.addr = addr;
		this.port = port;
		
		sock = new Socket(addr, port);
		DataInputStream dis = new DataInputStream(sock.getInputStream());
		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
		
		dos.writeInt(getServiceID());
		
		w = new RAWWriter(sock.getOutputStream());
		r = new RAWReader(sock.getInputStream());
	}
	
	protected void close(){
		if(sock != null){
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(w != null){
			w.close();
		}
		if(r != null){
			r.close();
		}
	}
	
	protected abstract int getServiceID();
}