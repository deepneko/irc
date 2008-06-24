package com.irc.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.irc.io.RAWReader;
import com.irc.io.RAWWriter;
import com.irc.io.Reader;
import com.irc.io.Writer;
import com.irc.service.Service;

/*
 * サーバ本体です
 * 各サービスをこのクラスに登録します
 * 
 * author: deepneko
 */
public class Server implements Runnable{

	private Map<Integer, Class> serviceMap;
	private int port;
	private ServerSocket serverSock;
	
	public Server(int port){
		this.port = port;
		serviceMap = new ConcurrentHashMap<Integer, Class>();
	}
	
	public boolean addService(Class clazz){
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(obj instanceof Service){
			serviceMap.put(((Service)obj).getServiceID(), clazz);
			return true;
		}
		
		return false;
	}
	
	public void start() throws IOException{
		serverSock = new ServerSocket(port);
		Thread th = new Thread(this);
		th.start();
	}
	
	public void close() throws IOException{
		if(serverSock != null){
			serverSock.close();
			serverSock = null;
		}
	}
	
	public void run(){
		try{
			while(serverSock != null){
				Socket sock = serverSock.accept();
				DataInputStream dis = null;
				//DataOutputStream dos = null;

				try {
					sock.setSoTimeout(1000);
					dis = new DataInputStream(sock.getInputStream());
					//dos = new DataOutputStream(sock.getOutputStream());
					
					int serviceID = dis.readInt();
					
					Class clazz = serviceMap.get(serviceID);
					if(clazz != null){
						try {
							Service service = (Service)clazz.newInstance();
							
							Reader r = new RAWReader(sock.getInputStream());
							Writer w = new RAWWriter(sock.getOutputStream());
							
							service.init(sock, w, r);
							service.start();
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
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