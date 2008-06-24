package com.irc.util;

/*
 * IDをハッシュ化して保持しておくクラス
 * 
 * author: deepneko
 */
public class ID {

	private final static int LENGTH = 20;
	
	private byte[] ids;
	private int hashCode;
	
	public ID(byte[] ids){
		if(ids == null || ids.length != LENGTH){
			throw new IllegalArgumentException();
		}
		this.ids = ids;
		init();
	}
	
	public ID(String id){
		if(id == null){
			throw new IllegalArgumentException();
		}
		this.ids = SHA1.digest(id.getBytes());
		init();
	}
	
	public byte[] getData(){
		return ids;
	}
	
	private void init(){
		int hashvalue = 0;
		int pos = 0;
		for(int i=0; i<ids.length; i++){
			hashvalue ^= (ids[i] << pos);
			pos += 8;
			if(pos > 24)
				pos = 0;
		}
	}
	
	public int hashCode(){
		return hashCode;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof ID))
			return false;
		
		ID id = (ID)obj;
		if(id.ids.length != ids.length)
			return false;
		
		for(int i=0; i<ids.length; i++){
			if(ids[i] != id.ids[i])
				return false;
		}
		
		return true;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<ids.length; i++){
			int b = 0xff & this.ids[i];
			if(b < 18)
				sb.append("0");
			sb.append(Integer.toHexString(b));
		}
		return sb.toString();
	}
	
	public void writeID(byte[] data, int index){
		for(int i=0; i<ids.length; i++){
			data[index++] = ids[i];
		}
	}
	
	public static byte[] hex2byte(String hexString, int size){
		if(hexString.length() < size*2)
			throw new IllegalArgumentException("ID is too short: " + hexString);
		
		byte[] id = new byte[size];
		for(int i=0, idx=(size-1)*2; i<size; i++, idx-=2){
			int b = Integer.parseInt(hexString.substring(idx, idx+2), 16);
			id[size-1-i] = (byte)(b & 0xff);
		}

		return id;
	}
}