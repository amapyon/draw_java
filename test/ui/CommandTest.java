package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import ui.Command;

public class CommandTest {

	@Test
	public void testEmpty() {
		Command c = new Command("");
		assertFalse(c.is("help"));
		assertFalse(c.is(""));
	}

	@Test
	public void testCommandOnly() {
		Command c = new Command("help");
		assertTrue(c.is("help"));
		assertFalse(c.is("HELP"));
		
		c = new Command("  help  ");
		assertTrue(c.is("help"));
		
		c = new Command("text (50, 40, \"森にて\", 34)");
		assertTrue(c.is("text"));
	}

	@Test
	public void testOptions() {
		Command c = new Command("");
		assertEquals("", c.getOptions());

		c = new Command("help");
		assertEquals("", c.getOptions());

		c = new Command("text (60, 80, \\2018年6月27日\\n森に行った。(vol1,1)\", 12) ");
		assertEquals("(60, 80, \\2018年6月27日\\n森に行った。(vol1,1)\", 12)", c.getOptions());
	}

	@Test
	public void testTextOption() {
		String commandLine = "text(60, 80, \"2018年6月27日\\n森に行った。(vol1,1)\", 12)";
		Command cmd = new Command(commandLine);
		assertEquals(60, cmd.getTextOptionInt(1));
		assertEquals(80, cmd.getTextOptionInt(2));
		assertEquals("2018年6月27日\\n森に行った。(vol1,1)", cmd.getTextOptionString(3));
		assertEquals(12, cmd.getTextOptionInt(4));
	}

	@Test
	public void testImageOption() {
		String commandLine = "image (80, 150, 100, 50, \"https://eiwasec.files.wordpress.com/2018/06/img64_64.png\") ";
		Command cmd = new Command(commandLine);
		assertEquals(80, cmd.getImageOptionInt(1));
		assertEquals(150, cmd.getImageOptionInt(2));
		assertEquals(100, cmd.getImageOptionInt(3));
		assertEquals(50, cmd.getImageOptionInt(4));
		assertEquals("https://eiwasec.files.wordpress.com/2018/06/img64_64.png", cmd.getImageOptionString(5));
	}
	
	@Test
	public void testGroupOption() {
		String commandLine = "group(" 
				+ "  text (60, 80, \"2018年6月27日\\n森に行った。(vol1,1),\", 12), "
				+ "  image (80, 150, 100, 50, \"https://eiwasec.files.wordpress.com/2018/06/img64_64.png\") "
				+")";
		Command cmd = new Command(commandLine);
		Command nextCommand = cmd.getGroupNextCommand();
		assertTrue(nextCommand.is("text"));
		nextCommand = cmd.getGroupNextCommand();
		assertTrue(nextCommand.is("image"));

	}

}
