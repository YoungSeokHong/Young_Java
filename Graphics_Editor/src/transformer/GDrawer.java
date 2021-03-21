package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shape.GPolygon;
import shape.GShape;

public class GDrawer extends GTransformer {

	public GDrawer(GShape selectedShape) {
		super(selectedShape);
	}

	@Override
	public void initTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().setLocation(x, y);
		this.getShape().draw(g2D);
	}

	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		if (this.getShape().isSelected() == true) {
			this.getShape().draw(g2D);
			this.getShape().resize(x, y);
			this.getShape().draw(g2D);
		}
	}
	
	public void continueTransforming(Graphics2D g2d, Point p) {
		this.getShape().addPoint(p);
	}

	@Override
	public void finishTransforming(Graphics2D g2D, int x, int y) {

	}
	
	public void continueDrawing(Point p) {
		((GPolygon) selectedShape).continueDrawing(p);
	}

	@Override
	public void moveALittle() {
		// TODO Auto-generated method stub
		
	}

}
