package draw;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import engine.RenderingEngine;

public class Document {
	
	private double width;
	private double height;
	private Color fgColor = Color.BLACK;
	private Color bgColor = Color.WHITE;
	private String filename = "Document.bmp";
	
	private ArrayList<Content> contents = null;
	
	public Document(double width, double height, String filename) {
		this(width, height);
		this.filename = filename;
	}
	
	public Document(double width, double height) {
		this.width = width;
		this.height = height;
		this.contents = new ArrayList<Content>();
	}
	
	public void setForgoundColor(Color fgColor) {
		this.fgColor = fgColor;
	}
	
	public void setBackgroudColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	public Content addContent(Content content) {
		contents.add(content);
		return content;
	}
	
	public void draw() throws IOException {
		RenderingEngine.initialize(width, height, fgColor, bgColor);
		contents.forEach((c)->{c.draw();});
		RenderingEngine.flush(filename);
	}
}
