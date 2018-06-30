package draw;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ImageTest {

	@Test
	public void testExistHttpsPng() throws IOException {
		Document doc = new Document(210, 297);
		String url = "https://eiwasec.files.wordpress.com/2018/06/img64_64.png";
		Image bitmap = new Image(80, 150, 100, 50, url);
		doc.addContent(bitmap);
		doc.draw();
		
	}

}
