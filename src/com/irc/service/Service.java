package com.irc.service;

import java.io.IOException;
import java.net.Socket;

import com.irc.io.Reader;
import com.irc.io.Writer;

/*
 * 各サービスの基底となるクラス
 * 
 * author: deepneko
 */
public abstract class Service extends Thread{

	protected Writer w;
	protected Reader r;
	protected Socket sock;
	
	public void init(Socket sock, Writer w, Reader r){
		this.sock = sock;
		this.w = w;
		this.r = r;
	}
	
	public abstract int getServiceID();

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
}