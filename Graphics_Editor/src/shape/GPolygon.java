package shape;

import java.awt.Point;
import java.awt.Polygon;

public class GPolygon extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private Polygon polygon;
	public GPolygon() {
		super(new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	public GShape newInstance() {
		return new GPolygon();
	}
	
	public void setLocation(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	public void addPoint(Point p) {
		this.polygon.addPoint((int)p.getX(), (int)p.getY());
	}
	
	
	public void resize(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}
	@Override
	public void move(int newX, int newY) {
		
	}
	@Override
	public void saveCurrentPosition(int x, int y) {
	}
	public void continueDrawing(Point p) {
		this.polygon.addPoint(p.x, p.y);

	}
}
