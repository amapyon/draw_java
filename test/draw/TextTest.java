package draw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ui.Console;

public class TextTest {

	@Test
	public void testOneText() throws Exception {
		Document doc = new Document(210.0, 297.0, "testOneText.bmp");
		Text t = new Text(50, 40, "森にて", 24);
		doc.addContent(t);
		doc.drawToFile();
	}

	@Test
	public void testScale() throws Exception {
		Document doc = new Document(210.0, 297.0);
		doc.addContent(new Text(0, 0, "-0", 11));
		doc.addContent(new Text(0, 10, "-", 11));
		doc.addContent(new Text(0, 20, "-", 11));
		doc.addContent(new Text(0, 30, "-", 11));
		doc.addContent(new Text(0, 40, "-", 11));
		doc.addContent(new Text(0, 50, "-50", 11));
		doc.addContent(new Text(0, 60, "-", 11));
		doc.addContent(new Text(0, 70, "-", 11));
		doc.addContent(new Text(0, 80, "-", 11));
		doc.addContent(new Text(0, 90, "-", 11));
		doc.addContent(new Text(0, 100, "-100", 11));
		doc.addContent(new Text(0, 110, "-", 11));
		doc.addContent(new Text(0, 120, "-", 11));
		doc.addContent(new Text(0, 130, "-", 11));
		doc.addContent(new Text(0, 140, "-", 11));
		doc.addContent(new Text(0, 150, "-150", 11));
		doc.addContent(new Text(0, 160, "-", 11));
		doc.addContent(new Text(0, 170, "-", 11));
		doc.addContent(new Text(0, 180, "-", 11));
		doc.addContent(new Text(0, 190, "-", 11));
		doc.addContent(new Text(0, 200, "-200", 11));
		doc.addContent(new Text(0, 210, "-", 11));
		doc.addContent(new Text(0, 220, "-", 11));
		doc.addContent(new Text(0, 230, "-", 11));
		doc.addContent(new Text(0, 240, "-", 11));
		doc.addContent(new Text(0, 250, "-250", 11));
		doc.addContent(new Text(0, 260, "-", 11));
		doc.addContent(new Text(0, 270, "-", 11));
		doc.addContent(new Text(0, 280, "-", 11));
		doc.addContent(new Text(0, 290, "-", 11));
		doc.addContent(new Text(10, 0, "|", 11));
		doc.addContent(new Text(20, 0, "|", 11));
		doc.addContent(new Text(30, 0, "|", 11));
		doc.addContent(new Text(40, 0, "|", 11));
		doc.addContent(new Text(50, 0, "|50", 11));
		doc.drawToFile();
	}
	
	@Test
	void testBorderdText() throws Exception {
		Document doc = new Document(210.0, 297.0);
		doc.addContent(new BorderedText(30, 50, "あいうえお", 11));
		doc.drawToWindow();
		Console.waitEnter();
	}

	@Test
	void testWrappableText() throws Exception {
		Document doc = new Document(210.0, 297.0);
		doc.addContent(new WrappableText(30, 50, "あいうえお\nかきくけこ\nさしすせそ", 18));
		doc.drawToWindow();
		Console.waitEnter();
	}
}
