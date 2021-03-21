package shape;

import java.awt.geom.Rectangle2D;

public class GRectangle extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private Rectangle2D rectangle;
	private int px, py;	
	
	public GRectangle() {
		super(new Rectangle2D.Double(0, 0 , 0, 0));
		this.rectangle = (Rectangle2D.Double)this.getShape();
	}
	public GShape newInstance() {
		return new GRectangle();
	}
	
	public void setLocation(int x, int y) {
		this.rectangle.setFrame(x, y, 
				this.rectangle.getWidth(), this.rectangle.getHeight());
	}
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x = this.rectangle.getX() + dx;
		double y = this.rectangle.getY() + dy;
		this.rectangle.setFrame(x, y, 
				this.rectangle.getWidth(), this.rectangle.getHeight());		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize(int newX, int newY) {
		double w = newX - this.rectangle.getX();
		double h = newY - this.rectangle.getY();
		this.rectangle.setFrame(this.rectangle.getX(), this.rectangle.getY(), w, h);
	}
}
