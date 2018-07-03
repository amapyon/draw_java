package draw;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import ui.Console;


public class ImageTest {
	@AfterAll
	static void tearDownAll() {
		Console.waitEnter();
	}
	
	@Tag("no-assert")
	@Test
	public void testExistHttpsJpg() throws Exception {
		Document doc = new Document(210, 297);
		String url = "https://eiwasec.files.wordpress.com/2018/07/02_s.jpg";
		Image image = new Image(80, 105, 100, 100, url);
		doc.addContent(image);
		doc.drawToWindow();
	}
	
	@Test
	public void testNotExist() throws Exception {
		String url = "https://eiwasec.files.wordpress.com/NOT_FOUND.jpg";
		assertThrows(IOException.class, () -> new Image(80, 105, 100, 100, url));
	}

	@Tag("no-assert")
	@Test
	public void testLocalFile() throws Exception {
		Document doc = new Document(210, 297);
		String url = "file:./resource/02_m.jpg";
		Image image = new Image(80, 105, 100, 100, url);
		doc.addContent(image);
		doc.drawToWindow();
	}
}
