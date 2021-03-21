package shape;

import java.awt.geom.RoundRectangle2D;

public class GRoundRectangle extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private RoundRectangle2D roundRectangle;
	private int px, py;	
	
	public GRoundRectangle() {
		super(new RoundRectangle2D.Double(0, 0 , 0, 0, 50, 50));
		this.roundRectangle = (RoundRectangle2D.Double)this.getShape();
	}
	public GShape newInstance() {
		return new GRoundRectangle();
	}
	
	public void setLocation(int x, int y) {
		this.roundRectangle.setFrame(x, y, 
				this.roundRectangle.getWidth(), this.roundRectangle.getHeight());
	}
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x = this.roundRectangle.getX() + dx;
		double y = this.roundRectangle.getY() + dy;
		this.roundRectangle.setFrame(x, y, 
				this.roundRectangle.getWidth(), this.roundRectangle.getHeight());		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize(int newX, int newY) {
		double w = newX - this.roundRectangle.getX();
		double h = newY - this.roundRectangle.getY();
		this.roundRectangle.setFrame(this.roundRectangle.getX(), this.roundRectangle.getY(), w, h);
	}
}
