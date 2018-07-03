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
	private static final double INCH = 25.4;// 単位mm
	private static final int DPI = 72; // 1インチ当たりのピクセル数
	private static final double SCALE = DPI / INCH;

	/**
	 * キャンバスの幅(単位はピクセル)
	 */
	private static int canvasWidth = DPI;

	/**
	 * キャンバスの高さ(単位はピクセル)
	 */
	private static int canvasHeight = DPI;

	private static BufferedImage canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_3BYTE_BGR);
	private static Graphics2D g = canvas.createGraphics();

	private static Color fgColor = Color.BLACK;
	private static Color bgColor = Color.WHITE;
	private static int fontSize = 11;

	/**
	 * 初期化
	 * 
	 * @param width 幅(単位はmm)
	 * @param height 高さ(単位はmm)
	 */
	public static void initialize(double width, double height) {
		// mmをピクセルに変換
		canvasWidth = toPixel(width);
		canvasHeight = toPixel(height);
		canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_3BYTE_BGR);

		g = canvas.createGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, canvasWidth, canvasHeight);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(fgColor);
		// System.out.printf("%d %d\n", pxWidth, pxHeight);
		g.setFont(new Font(null, 0, 11));
		drawString(0, 0, LocalDateTime.now().toString());
	}

	/**
	 * 初期化
	 * 
	 * @param width 幅(単位はmm)
	 * @param height 高さ(単位はmm)
	 * @param fgColor 描画色
	 * @param bgColor 背景色
	 */
	public static void initialize(double width, double height, Color fgColor, Color bgColor) {
		RenderingEngine.fgColor = fgColor;
		RenderingEngine.bgColor = bgColor;
		initialize(width, height);
	}

	/**
	 * フォントサイズを指定する
	 * 
	 * @param fontSize
	 */
	public static void setFontSize(int fontSize) {
		RenderingEngine.fontSize = fontSize;
	}

	/**
	 * 文字列を描画する
	 * 
	 * @param x x座標(単位はmm)
	 * @param y y座標(単位はmm)
	 * @param text 描画する文字列
	 */
	public static void drawString(double x, double y, String text) {
		g.setFont(new Font(null, 0, fontSize));
		FontMetrics fm = g.getFontMetrics();
		int pxX = toPixel(x);
		int pxY = toPixel(y);
		// System.out.printf("%d %d %d %d\n", pxX, pxY, fm.getAscent(), fm.getHeight());
		g.drawString(text, pxX, pxY + fm.getAscent());
	}

	/**
	 * 現在のフォントで指定した文字列を描画した際の高さを取得する
	 * 
	 * @return 文字列の表示高さ(単位はmm)
	 */
	public static double getStringHeight() {
		g.setFont(new Font(null, 0, fontSize));
		FontMetrics fm = g.getFontMetrics();
		return toMm(fm.getHeight());
	}

	/**
	 * 現在のフォントで指定した文字列を描画した際の幅を取得する
	 * 
	 * @param text 測定したい文字列
	 * @return 文字列の表示幅(単位はmm)
	 */
	public static double getStringWidth(String text) {
		g.setFont(new Font(null, 0, fontSize));
		FontMetrics fm = g.getFontMetrics();
		return toMm(fm.stringWidth(text));
	}

	/**
	 * 画像を描画する
	 * 
	 * @param x x座標(単位はmm)
	 * @param y y座標(単位はmm)
	 * @param width 幅(単位はmm)
	 * @param height 高さ(単位はmm)
	 * @param image
	 */
	public static void drawImage(double x, double y, double width, double height, BufferedImage image) {
		g.drawImage(image, toPixel(x), toPixel(y), toPixel(width), toPixel(height), null);
	}

	/**
	 * 線を描画する
	 * 
	 * @param x1 始点のx座標(単位はmm)
	 * @param y1 始点のy座標(単位はmm)
	 * @param x2 終点のx座標(単位はmm)
	 * @param y2 終点のy座標(単位はmm)
	 */
	public static void drawLine(double x1, double y1, double x2, double y2) {
		g.drawLine(toPixel(x1), toPixel(y1), toPixel(x2), toPixel(y2));
	}

	/**
	 * mmの値を、ピクセルに変換する
	 * 
	 * @param value mmの値
	 * @return ピクセル
	 */
	private static int toPixel(double value) {
		return (int) (value * SCALE);
	}

	/**
	 * ピクセルの値を、mmに変換する
	 * 
	 * @param value ピクセルの値
	 * @return mm
	 */
	private static double toMm(double value) {
		return value / SCALE;
	}

	/**
	 * 指定したファイルに画像をbmp形式で出力する
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public static void flush(String filename) throws IOException {
		ImageIO.write(canvas, "BMP", new File(filename));
	}

	/**
	 * 画像をウィンドウに出力する
	 */
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
