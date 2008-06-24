package com.irc.io;

import java.io.IOException;

/*
 * author: deepneko
 */
public interface Writer {

	public abstract void writeByte(byte b) throws IOException;
	
	public abstract void writeDouble(double d) throws IOException;
	
	public abstract void writeInt(int i) throws IOException;
	
	public abstract void writeBytes(byte[] data) throws IOException;
	
	public abstract void flush() throws IOException;
	
	public abstract void close();
}