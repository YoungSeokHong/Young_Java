package shape;

import java.awt.geom.Ellipse2D;

public class GEllipse extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private Ellipse2D ellipse;
	private int px, py;	
	
	public GEllipse() {
		super(new Ellipse2D.Double(0, 0 , 0, 0));
		this.ellipse = (Ellipse2D.Double)this.getShape();
	}
	public GShape newInstance() {
		return new GEllipse();
	}
	
	public void setLocation(int x, int y) {
		this.ellipse.setFrame(x, y, 
				this.ellipse.getWidth(), this.ellipse.getHeight());
	}
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x = this.ellipse.getX() + dx;
		double y = this.ellipse.getY() + dy;
		this.ellipse.setFrame(x, y, 
				this.ellipse.getWidth(), this.ellipse.getHeight());		
		this.px = newX;
		this.py = newY;
	}
	public void resize(int newX, int newY) {
		double w = newX - this.ellipse.getX();
		double h = newY - this.ellipse.getY();
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), w, h);
	}
	
}
