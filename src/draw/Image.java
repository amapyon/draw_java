package draw;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import engine.RenderingEngine;

public class Image extends Content {
	private double width;
	private double height;
	private String url;
	private BufferedImage image;

	public Image(double x, double y, double width, double height, String url) throws MalformedURLException, IOException {
		super(x, y);
		this.width = width;
		this.height = height;
		this.url = url;
		this.image = ImageIO.read(new URL(this.url));
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public void draw(double offsetX, double offsetY) {
		RenderingEngine.drawImage(offsetX + getX(), offsetY + getY(), width, height, image);

	}

}
