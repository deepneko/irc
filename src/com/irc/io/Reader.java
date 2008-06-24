package com.irc.io;

import java.io.IOException;

/*
 * author: deepneko
 */
public interface Reader {

	public abstract byte readByte() throws IOException;
	
	public abstract double readDouble() throws IOException;
	
	public abstract int readInt() throws IOException;
	
	public abstract int readBytes(byte[] data) throws IOException;
	
	public abstract void close();
	
}