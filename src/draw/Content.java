package draw;


public abstract class Content {
	private double x;
	private double y;

	public Content(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void draw() {
		draw(0.0, 0.0);
	}
	
	public abstract void draw(double offsetX, double offsetY);
}
