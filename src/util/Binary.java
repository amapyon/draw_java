package util;

public class Binary {
	private static String cr = System.getProperty("line.separator");

	public static String dump(byte[] b, int length) {
		StringBuilder s = new StringBuilder();
		int max = length < b.length ? length : b.length;
		for (int i = 0; i < max; i += 16) {
			int len = i + 16 < max ? 16 : max % 16;
			s.append(toHex(b, i, len));
			s.append(toChar(b, i, len));
			s.append(cr);
		}
		return s.toString();
	}
	
	public static String toHex(byte[] b, int off, int len) {
		StringBuilder s = new StringBuilder();
		for (int i = off; i < (off + len); i++) {
			s.append(String.format(" %02X", b[i]));
		}
		return s.toString();
		
	}

	public static String toChar(byte[] b, int off, int len) {
		StringBuilder s = new StringBuilder();
		for (int i = off; i < (off + len); i++) {
			s.append(' ');
			if (' ' <= b[i] && b[i] <= '~') {
				s.append((char)b[i]);
			} else {
				s.append('.');
			}
		}
		return s.toString();
		
	}
}
