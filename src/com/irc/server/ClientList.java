package com.irc.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


import com.irc.IRCSystem;
import com.irc.util.StringParser;

/*
 * ユーザ情報を保持しておくデータベースクラス
 * 全てstaticで気持ち悪いぜ
 * 
 * author: deepneko
 */
public class ClientList {

	static ConcurrentHashMap<String, String> clientList;
	static ConcurrentHashMap<String, InetAddress> loginList;
	static ConcurrentHashMap<String, String> machineidList;

	public static void init(){
		clientList = new ConcurrentHashMap<String, String>();

		try {
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
										new FileInputStream(new File(IRCSystem.CLIENT_LIST))));
			String str = "";
			String[] strArray = new String[2];
			while((str=br.readLine()) != null){
				strArray = StringParser.divide(str, ",");
				clientList.put(strArray[0], strArray[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginList = new ConcurrentHashMap<String, InetAddress>();
		machineidList = new ConcurrentHashMap<String, String>();
	}
	
	public static void debug(){
		Set<String> key = clientList.keySet();
		Iterator<String> it = key.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public static String getLoginNameList(){
		Set<String> key = loginList.keySet();
		Iterator<String> it = key.iterator();
		String str = "";
		while(it.hasNext()){
			str += it.next() + " ";
		}
		return str;
	}

	public static boolean auth(String id, String pass){
		if(clientList.containsKey(id))
		{
			if(clientList.get(id).equals(pass))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isUser(String id){
		return loginList.containsKey(id);
	}
	
	public static String getMachineid(String id){
		if(machineidList.containsKey(id))
			return machineidList.get(id);
		return "";
	}
	
	public static String login(String id, InetAddress addr, String machineid){
		while(loginList.containsKey(id)){
			if(loginList.get(id).equals(addr)){
				return id;
			}
			id += "_";
		}
		loginList.put(id, addr);
		machineidList.put(id, machineid);

		return id;
	}
	
	public static boolean logout(String id){
		if(machineidList.containsKey(id)){
			machineidList.remove(id);
		}
		if(loginList.containsKey(id)){
			loginList.remove(id);
			return true;
		}
		return false;
	}
}