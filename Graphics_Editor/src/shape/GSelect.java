package shape;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

public class GSelect extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private Rectangle2D rectangle;
	private int px, py;	
	
	public GSelect() {
		super(new Rectangle2D.Double(0, 0 , 0, 0));
		this.rectangle = (Rectangle2D.Double)this.getShape();
	}
	public GShape newInstance() {
		return new GSelect();
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
	
	public void draw(Graphics2D g2D) {
		Stroke savedStroke = g2D.getStroke();
		Stroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f,
				new float[] { 2.f, 5.f }, 0.0f);
		g2D.setStroke(stroke);
		g2D.draw(this.rectangle);
		g2D.setStroke(savedStroke);
	}
}
