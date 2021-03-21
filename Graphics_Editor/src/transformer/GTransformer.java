package transformer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import shape.GShape;

public abstract class GTransformer {
	protected GShape selectedShape;
	protected Point2D previous, center;
	
	public GTransformer(GShape selectedShape) {
		this.selectedShape = selectedShape;
		this.previous = new Point2D.Double();
		this.center = new Point2D.Double();

	}
	
	public GShape getShape() {	return this.selectedShape;	}
	public Point2D getPrevious(){ return this.previous;}
	
	public abstract void initTransforming(Graphics2D g2D, int x, int y);
	public abstract void keepTransforming(Graphics2D g2D, int x, int y);
	public abstract void finishTransforming(Graphics2D g2D, int x, int y);

	public void continueTransforming(Graphics2D g2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public abstract void moveALittle();
}
