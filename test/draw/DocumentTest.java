package draw;

import java.awt.Color;
import java.io.IOException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DocumentTest {

	@Tag("no-assert")
	@Test
	public void testDrawToFile() throws IOException {
		Document doc = new Document(210, 297);
		doc.setForgoundColor(Color.BLACK);
		doc.setBackgroudColor(new Color(0xFF, 0xFF, 0xCF));
		
		doc.drawToFile();
	}

	@Tag("no-assert")
	@Test
	public void testDrawToWindow() throws IOException {
		Document doc = new Document(210, 297);
		doc.setForgoundColor(Color.BLACK);
		doc.setBackgroudColor(new Color(0xFF, 0xFF, 0xCF));
		
		doc.drawToWindow();
	}
}
