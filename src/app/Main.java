package app;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import ui.Command;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line;
		while ((line = scanner.nextLine()) != null) {
			Command c = new Command(line);
			if (c.is("quit")) {
				break;
			} else if (c.is("string")) {
				String options = c.getOptions();
			}
		}
		scanner.close();
	}

}
