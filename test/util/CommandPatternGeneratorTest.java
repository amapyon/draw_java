package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommandPatternGeneratorTest {

	@Test
	void testGenerateNoOption() {
		assertEquals("\\s*command\\s*", CommandPatternGenerator.generate("command"));
	}
	
	@Test
	void testGenerateEmptyOption() throws Exception {
		assertEquals("\\s*command\\s*", CommandPatternGenerator.generate("command", null));
		assertEquals("\\s*command\\s*", CommandPatternGenerator.generate("command", ""));
	}

	@Test
	void testGenerateOneStringOption() throws Exception {
		assertEquals("\\s*command\\s*\\(\\s*\\\"(.*)\\\"\\s*\\)", CommandPatternGenerator.generate("command", "S"));
	}

	@Test
	void testGenerateOneNumberOption() throws Exception {
		assertEquals("\\s*command\\s*\\(\\s*(\\d+)\\s*\\)", CommandPatternGenerator.generate("command", "N"));
	}

	@Test
	void testGenerateTwoOptions() throws Exception {
		assertEquals("\\s*command\\s*\\(\\s*(\\d+)\\s*,\\s*\\\"(.*)\\\"\\s*\\)", CommandPatternGenerator.generate("command", "N,S"));
	}
	
	@Test
	void testGenereteImage() throws Exception {
		String pattern = "\\s*image\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*\\\"(.*)\\\"\\s*\\)";
		assertEquals(pattern, CommandPatternGenerator.generate("image", "N,N,N,N,S"));
		
	}

	@Test
	void testGenereteGroup() throws Exception {
		String pattern = "\\s*group\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(.+)\\s*\\)";
		assertEquals(pattern, CommandPatternGenerator.generate("group", "N,N,B"));
		
	}
}
