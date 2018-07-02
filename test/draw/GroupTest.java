package draw;

import java.awt.Color;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Groupのユニットテスト")
class GroupTest {

	@DisplayName("要素を持たないグループ")
	@Tag("no-assert")
	@Test
	void testEmpty() throws IOException {
		Document doc = new Document(210, 297, "GroupTest.bmp");
		Group group = new Group(100, 100);
		doc.addContent(group);
		doc.drawToFile();
	}
	
	@DisplayName("1つの要素を持ったグループ")
	@Tag("no-assert")
	@Test
	void testText() throws IOException {
		Document doc = new Document(210, 297, "GroupTest#Text.bmp");
		Group group = new Group(100, 100);
		Text text = new Text(20, 20, "Text in Group.", 11);

		group.addContent(text);
		doc.addContent(group);
		doc.drawToFile();
	}

	@DisplayName("2つの要素を持ったグループ")
	@Tag("no-assert")
	@Test
	void testTextAndImage() throws IOException {
		Document doc = new Document(210, 297, "GroupTest#TextAndImage.bmp");
		doc.setBackgroudColor(new Color(255, 255, 196));
		
		Group group = new Group(100, 100);
		Text text = new Text(20, 20, "Text in Group.", 11);
		group.addContent(text);
		
		Image image = new Image(20, 50, 100, 50, "hhttps://eiwasec.files.wordpress.com/2018/07/02_s.jpg");
		group.addContent(image);
		
		doc.addContent(group);
		doc.drawToFile();
	}
}
