package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import draw.Content;
import draw.Group;
import draw.Image;
import draw.Text;
import ui.Command;

public class ContentFactoryTest {

	@Test
	void testCreateText() {
		Command cmd = new Command("text (60, 80, \"2018年6月27日\\n森に行った。(vol1,1)\", 12) ");
		Content content = ContentFactory.create(cmd);
		
		assertTrue(content instanceof Text);
	}

	@Test
	void testCreateImage() {
		Command cmd = new Command("image (80, 150, 100, 50, \"https://eiwasec.files.wordpress.com/2018/07/02_s.jpg\") ");
		Content content = ContentFactory.create(cmd);
		
		assertTrue(content instanceof Image);
	}
	
	@Test
	void testCreateGroup() {
		String commandLine = "group(100, 110,\n" 
				+ "  text (60, 80, \"2018年6月27日\\n森に行った。(vol1,1)\", 12), "
				+ "  image (80, 150, 100, 50, \"https://eiwasec.files.wordpress.com/2018/07/02_s.jpg\") "
				+")";
		
		Command cmd = new Command(commandLine);
		Content content = ContentFactory.create(cmd);

		assertTrue(content instanceof Group);
	}

}
