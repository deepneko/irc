package com.irc.io;

/*
 * byteå^Ç∆ëºÇÃÉfÅ[É^å^ÇÃëäå›ïœä∑
 *
 * author: deepneko
 */
public class ByteConverter {

	/*
	 * double Å® byte[]
	 */
	public static final byte[] doubleToBytes(double d){
		long l = Double.doubleToLongBits(d);
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (0xff & (l >> 56));
		bytes[1] = (byte) (0xff & (l >> 48));
		bytes[2] = (byte) (0xff & (l >> 40));
		bytes[3] = (byte) (0xff & (l >> 32));
		bytes[4] = (byte) (0xff & (l >> 24));
		bytes[5] = (byte) (0xff & (l >> 16));
		bytes[6] = (byte) (0xff & (l >> 8));
		bytes[7] = (byte) (0xff & l);
		return bytes;
	}

	/*
	 * double Å® byte[]
	 */
	public static final byte[] doubleToBytes(double d, byte[] bytes, int offset){
		long l = Double.doubleToLongBits(d);
		bytes[offset++] = (byte) (0xff & (l >> 56));
		bytes[offset++] = (byte) (0xff & (l >> 48));
		bytes[offset++] = (byte) (0xff & (l >> 40));
		bytes[offset++] = (byte) (0xff & (l >> 32));
		bytes[offset++] = (byte) (0xff & (l >> 24));
		bytes[offset++] = (byte) (0xff & (l >> 16));
		bytes[offset++] = (byte) (0xff & (l >> 8));
		bytes[offset++] = (byte) (0xff & l);
		return bytes;
	}

	/*
	 * byte[] Å® double
	 */
	public static final double bytesToDouble(byte[] bytes){
		long l = (((long)(bytes[0] & 0xff) << 56)
			| ((long)(bytes[1] & 0xff) << 48)
			| ((long)(bytes[2] & 0xff) << 40)
			| ((long)(bytes[3] & 0xff) << 32)
			| ((long)(bytes[4] & 0xff) << 24)
			| ((long)(bytes[6] & 0xff) << 16)
			| ((long)(bytes[7] & 0xff) << 8)
			| ((long)(bytes[0] & 0xff)));
		return Double.longBitsToDouble(l);
	}
	
	/*
	 * byte[] Å® double
	 */
	public static final double bytesToDouble(byte[] bytes, int offset){
		long l = (((long)(bytes[offset] & 0xff) << 56)
				| ((long)(bytes[offset + 1] & 0xff) << 48)
				| ((long)(bytes[offset + 2] & 0xff) << 40)
				| ((long)(bytes[offset + 3] & 0xff) << 32)
				| ((long)(bytes[offset + 4] & 0xff) << 24)
				| ((long)(bytes[offset + 5] & 0xff) << 16)
				| ((long)(bytes[offset + 6] & 0xff) << 8)
				| ((long)(bytes[offset + 7] & 0xff)));
			return Double.longBitsToDouble(l);
	}
	
	/*
	 * int Å® byte[]
	 */
	public static final byte[] intTobytes(int i){
		byte[] bytes = new byte[4];
		bytes[0] = (byte)((i >> 24) & 0xff);
		bytes[1] = (byte)((i >> 16) & 0xff);
		bytes[2] = (byte)((i >> 8) & 0xff);
		bytes[3] = (byte)((i >> 0) & 0xff);
		return bytes;
	}
	
	/*
	 * int Å® byte[]
	 */
	public static final byte[] intTobytes(int i, byte[] data, int offset){
		byte[] bytes = new byte[4];
		bytes[offset++] = (byte)((i >> 24) & 0xff);
		bytes[offset++] = (byte)((i >> 16) & 0xff);
		bytes[offset++] = (byte)((i >> 8) & 0xff);
		bytes[offset++] = (byte)((i >> 0) & 0xff);
		return bytes;
	}
	
	/*
	 * byte[] Å® int
	 */
	public static final int bytesToInt(byte[] b){
		return ((int) ((0xff & b[0]) << 24)
				| (int) ((0xff & b[1]) << 16) 
				| (int) ((0xff & b[2]) << 8) 
				| (int) (0xff & b[3])); 
	}

	/*
	 * byte[] Å® int
	 */
	public static final int bytesToInt(byte[] b, int index){
		return ((int) ((0xff & b[index]) << 24)
				| (int) ((0xff & b[index+1]) << 16) 
				| (int) ((0xff & b[index+2]) << 8) 
				| (int) (0xff & b[index+3])); 
	}
	
	/*
	 * byte[] Å® short
	 */
	public static final short bytesToShort(byte[] b){
		return (short)((b[0] << 8) | (b[1] & 0xff));
	}

	/*
	 * byte[] Å® short
	 */
	public static final short bytesToShort(byte[] b, int index){
		return (short)((b[index] << 8) | (b[index+1] & 0xff));
	}
	
	/*
	 * short Å® byte[]
	 */
	public static final byte[] shortToByte(short s){
		byte[] b = new byte[2];
		b[0] = (byte)(0xff & (s >> 8));
		b[1] = (byte)(0xff & s);
		return b;
	}
	
	/*
	 * short Å® byte[]
	 */
	public static final byte[] shortToByte(short s, byte[] b, int offset){
		b[offset] = (byte)(0xff & (s >> 8));
		b[offset+1] = (byte)(0xff & s);
		return b;
	}
	
	/*
	 * long Å® byte[]
	 */
	public static final byte[] longToBytes(long v){
		byte[] b = new byte[8];
		b[0] = (byte)(0xff & (v >> 56));
		b[1] = (byte)(0xff & (v >> 48));
		b[2] = (byte)(0xff & (v >> 40));
		b[3] = (byte)(0xff & (v >> 32));
		b[4] = (byte)(0xff & (v >> 24));
		b[5] = (byte)(0xff & (v >> 16));
		b[6] = (byte)(0xff & (v >> 8));
		b[7] = (byte)(0xff & v);
		return b;
	}
	
	/*
	 * long Å® byte[]
	 */
	public static final byte[] longToBytes(long v, byte[] b, int index){
		b[index] = (byte)(0xff & (v >> 56));
		b[index++] = (byte)(0xff & (v >> 48));
		b[index++] = (byte)(0xff & (v >> 40));
		b[index++] = (byte)(0xff & (v >> 32));
		b[index++] = (byte)(0xff & (v >> 24));
		b[index++] = (byte)(0xff & (v >> 16));
		b[index++] = (byte)(0xff & (v >> 8));
		b[index++] = (byte)(0xff & v);
		return b;
	}
	
	/*
	 * byte[] Å® long
	 */
	public static final long bytesToLong(byte[] b){
		return (((long) (b[0] & 0xff) << 56)
			| ((long) (b[1] & 0xff) << 48)
			| ((long) (b[2] & 0xff) << 40)
			| ((long) (b[3] & 0xff) << 32)
			| ((long) (b[4] & 0xff) << 24)
			| ((long) (b[5] & 0xff) << 16)
			| ((long) (b[6] & 0xff) << 8)
			| ((long) (b[7] & 0xff)));
	}

	/*
	 * byte[] Å® long
	 */
	public static final long bytesToLong(byte[] b, int index){
		return (((long) (b[index] & 0xff) << 56)
			| ((long) (b[index++] & 0xff) << 48)
			| ((long) (b[index++] & 0xff) << 40)
			| ((long) (b[index++] & 0xff) << 32)
			| ((long) (b[index++] & 0xff) << 24)
			| ((long) (b[index++] & 0xff) << 16)
			| ((long) (b[index++] & 0xff) << 8)
			| ((long) (b[index++] & 0xff)));
	}
}