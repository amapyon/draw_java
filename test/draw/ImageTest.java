package draw;

import java.io.IOException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class ImageTest {

	@Tag("no-assert")
	@Test
	public void testExistHttpsPng() throws IOException {
		Document doc = new Document(210, 297);
		String url = "https://eiwasec.files.wordpress.com/2018/07/02_s.jpg";
		Image bitmap = new Image(80, 105, 100, 100, url);
		doc.addContent(bitmap);
		doc.drawToFile();
		
	}

}
