package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RenderingEngine {
	private static final double INCH = 25.4;//mm
	private static final double DPI = 72;
	private static final double SCALE = DPI / INCH;

	private static BufferedImage canvas = new BufferedImage((int)DPI, (int)DPI, BufferedImage.TYPE_3BYTE_BGR);
	private static Graphics2D g = canvas.createGraphics();

	private static Color fgColor = Color.BLACK; 
	private static Color bgColor = Color.WHITE; 
	private static int fontSize = 11;
	
	public static void initialize(double width, double height) {
		int pxWidth = toPixel(width);
		int pxHeight = toPixel(height);
		canvas = new BufferedImage(pxWidth, pxHeight, BufferedImage.TYPE_3BYTE_BGR);
		g = canvas.createGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, pxWidth, pxHeight);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(fgColor);
//		System.out.printf("%d %d\n", pxWidth, pxHeight);
		g.setFont(new Font(null, 0, fontSize));
		drawString(0, 0, LocalDateTime.now().toString());
	}
	
	public static void initialize(double width, double height, Color fgColor, Color bgColor) {
		RenderingEngine.fgColor = fgColor;
		RenderingEngine.bgColor = bgColor;
		initialize(width, height);
	}

	public static void setFontSize(int fontSize) {
		RenderingEngine.fontSize = fontSize;
	}
	
	public static void drawString(double x, double y, String text) {
		g.setFont(new Font(null, 0, fontSize));
		FontMetrics fm = g.getFontMetrics();
		int pxX = toPixel(x);
		int pxY = toPixel(y);
//		System.out.printf("%f %f %d %d\n", (float)pxX, (float)pxY, fm.getAscent(), fm.getHeight());
		g.drawString(text, pxX, pxY + fm.getAscent());
	}
	
	public static void drawImage(double x, double y, double width, double height, BufferedImage image) {
		g.drawImage(image, toPixel(x), toPixel(y), toPixel(width), toPixel(height), null);
	}
	
	private static int toPixel(double value) {
		return (int)(value * SCALE);
	}
	
	public static void flush(String filename) throws IOException {
		ImageIO.write(canvas, "BMP", new File(filename));
	}

	public static void flushWindow() {
		@SuppressWarnings("serial")
		JPanel p = new JPanel() {
			BufferedImage image = copy(canvas);
			
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponents(g);
				g.drawImage(image, 0, 0, this);
			}

			private BufferedImage copy(BufferedImage canvas) {
			    BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), canvas.getType());
			    Graphics g = image.getGraphics();
			    g.drawImage(canvas, 0, 0, null);
			    return image;
			}
			
		};
		JFrame frame = new JFrame();
		frame.setSize(canvas.getWidth(), canvas.getHeight());
		frame.add(p);
		frame.setVisible(true);
		
	}


}
