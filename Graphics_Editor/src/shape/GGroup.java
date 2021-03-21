package shape;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GGroup extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private Rectangle2D group;
	private int px, py;
	private Vector<GShape> groupVector;
	
	
	public GGroup() {
		super(new Rectangle2D.Double(0, 0 , 0, 0));
		this.group = (Rectangle2D.Double)this.getShape();
		this.groupVector = new Vector<GShape>();
		}
	public GShape newInstance() {
		return new GRectangle();
	}
	
	public void setLocation(int x, int y) {
		this.group.setFrame(x, y, 
				this.group.getWidth(), this.group.getHeight());
	}
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x = this.group.getX() + dx;
		double y = this.group.getY() + dy;
		this.group.setFrame(x, y, 
				this.group.getWidth(), this.group.getHeight());		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize(int newX, int newY) {
		double w = newX - this.group.getX();
		double h = newY - this.group.getY();
		this.group.setFrame(this.group.getX(), this.group.getY(), w, h);
	}
	
	public void group(){
//		this.getShape().getBounds2D().union(src1, src2, dest);
	}
	
	public void add(GShape shape) {
//		this.groupVector.add(shape);
//		this.rectangle.union(this.groupVector.firstElement().getShape().getBounds(), shape.getShape().getBounds(),
//				this.rectangle);
		
		groupVector.add(0, shape);
		if (groupVector.size() == 1) {
			this.group = shape.getBounds();
		} else {
			this.group = this.group.createUnion(shape.getBounds());
		}
	}
	
	public void draw(Graphics2D g2D){
		g2D.draw(this.getShape());
	}
}
