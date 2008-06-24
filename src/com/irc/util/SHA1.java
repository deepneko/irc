package com.irc.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * ÉnÉbÉVÉÖä÷êî(SHA1)
 * 
 * author: deepneko
 */
public class SHA1 {
	public final static String SHA1 = "SHA1";
	
	public final static byte[] digest(String file_name) throws IOException{
		DigestInputStream inStream = null;
		
		try {
			inStream = new DigestInputStream(
					new BufferedInputStream(new FileInputStream(file_name)), MessageDigest.getInstance(SHA1));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] buf = new byte[1024];
		while(true){
			if(inStream.read(buf) <= 0)
				break;
		}
		inStream.close();
		
		MessageDigest md5 = inStream.getMessageDigest();
		return md5.digest();
	}
	
	public final static byte[] digest(byte[] data){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(SHA1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return digest.digest(data);
	}
	
	/*
	 * Test
	 */
	public static void main(String[] args){
		byte[] data = "deepneko".getBytes();
		byte[] d = digest(data);
		System.out.println(d.length);
		for(int i=0; i<d.length; i++)
			System.out.println(d[i] + " ");
	}
}