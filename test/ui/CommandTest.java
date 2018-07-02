package ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CommandTest {

	@Test
	public void testNull() {
		Command c = new Command(null);
		assertFalse(c.is("quit"));
		assertFalse(c.is(""));
	}

	@Test
	public void testEmpty() {
		Command c = new Command("");
		assertFalse(c.is("quit"));
		assertFalse(c.is(""));
	}

	@Test
	public void testCommandOnly() {
		Command c = new Command("quit");
		assertTrue(c.is("quit"));
		assertFalse(c.is("QUIT"));
		
		c = new Command("  quit  ");
		assertTrue(c.is("quit"));
		
		c = new Command("text (60, 80, \\2018年6月27日\\n森に行った。(vol1,1)\", 12) ");
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
		assertEquals(60, cmd.getOptionInt(1));
		assertEquals(80, cmd.getOptionInt(2));
		assertEquals("2018年6月27日\\n森に行った。(vol1,1)", cmd.getOptionString(3));
		assertEquals(12, cmd.getOptionInt(4));
	}

	@Test
	public void testOptionString() {
		String commandLine = "text(60, 80, \"2018年6月27日\\n森に行った。(vol1,1)\", 12)";
		Command cmd = new Command(commandLine);
		assertEquals("2018年6月27日\\n森に行った。(vol1,1)", cmd.getOptionString(3));
	}

	@Test
	public void testOptionInt() {
		String commandLine = "text(60, 80, \"2018年6月27日\\n森に行った。(vol1,1)\", 12)";
		Command cmd = new Command(commandLine);
		assertEquals(60, cmd.getOptionInt(1));
		assertEquals(80, cmd.getOptionInt(2));
	}

	@Test
	public void testImageOption() {
		String commandLine = "image (80, 150, 100, 50, \"https://eiwasec.files.wordpress.com/2018/07/02_s.jpg\") ";
		Command cmd = new Command(commandLine);
		assertEquals(80, cmd.getOptionInt(1));
		assertEquals(150, cmd.getOptionInt(2));
		assertEquals(100, cmd.getOptionInt(3));
		assertEquals(50, cmd.getOptionInt(4));
		assertEquals("https://eiwasec.files.wordpress.com/2018/07/02_s.jpg", cmd.getOptionString(5));
	}
	
	@Test
	public void testGroupOption() {
		String commandLine = "group(100, 110," 
				+ "  text (60, 80, \"2018年6月27日\\n森に行った。(vol1,1),\", 12),  "
				+ "  image (80, 150, 100, 50, \"https://eiwasec.files.wordpress.com/2018/07/02_s.jpg\") "
				+")";
		Command cmd = new Command(commandLine);
		assertTrue(cmd.is("group"));
		assertEquals(100, cmd.getOptionInt(1));
		assertEquals(110, cmd.getOptionInt(2));
		
		Command nextCommand = cmd.getNextCommand();
		assertTrue(nextCommand.is("text"));

		nextCommand = cmd.getNextCommand();
		assertTrue(nextCommand.is("image"));

		nextCommand = cmd.getNextCommand();
		assertNull(nextCommand);
	}

	@Test
	public void testFlushWithoutOption() {
		String commandLine = "flush()";
		Command cmd = new Command(commandLine);
		assertTrue(cmd.is("flush"));
		assertEquals("", cmd.getOptionString(1));
	}

	@Test
	public void testFlushWithOption() {
		String commandLine = "flush(\"Document.bmp\")";
		Command cmd = new Command(commandLine);
		assertTrue(cmd.is("flush"));
		assertEquals("Document.bmp", cmd.getOptionString(1));
	}

}
