package com.irc.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * author: deepneko
 */
public class RAWReader implements Reader{

	private DataInputStream dis;
	private BufferedInputStream bis;
	
	public RAWReader(InputStream is) throws IOException{
		dis = new DataInputStream(is);
		bis = new BufferedInputStream(is);
	}
	
	public void close() {
		if(dis != null){
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(bis != null){
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public byte readByte() throws IOException {
		return dis.readByte();
	}

	public int readBytes(byte[] data) throws IOException {
		return bis.read(data);
	}

	public double readDouble() throws IOException {
		return dis.readDouble();
	}

	public int readInt() throws IOException {
		return dis.readInt();
	}

}
