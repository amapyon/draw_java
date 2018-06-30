package util;

public class ByteArray {
	private byte[] data;
	
	public ByteArray() {
		this.data = new byte[0];
	}
	
	public byte[] append(byte[] appendData) {
		return append(appendData, appendData.length);
	}

	public byte[] append(byte[] appendData, int len) {
		byte[] newData = new byte[this.data.length + len];
		System.arraycopy(this.data, 0, newData, 0, this.data.length);
		System.arraycopy(appendData, 0, newData, this.data.length, len);
		this.data = newData;
		return this.data;
	}
	
	public byte[] getData() {
		return this.data;
	}
}
