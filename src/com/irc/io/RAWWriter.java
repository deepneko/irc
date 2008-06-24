package com.irc.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * author: deepneko
 */
public class RAWWriter implements Writer{

	DataOutputStream dos;
	BufferedOutputStream bos;

	public RAWWriter(OutputStream os){
		dos = new DataOutputStream(os);
		bos = new BufferedOutputStream(os);
	}
	
	public void close() {
		if(dos != null){
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(bos != null){
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeByte(byte b) throws IOException {
		dos.writeByte(b);
	}

	public void writeBytes(byte[] data) throws IOException {
		bos.write(data);
	}

	public void writeDouble(double d) throws IOException {
		dos.writeDouble(d);
	}

	public void writeInt(int i) throws IOException {
		dos.writeInt(i);
	}

	public void flush() throws IOException {
		dos.flush();
		bos.flush();
	}

}