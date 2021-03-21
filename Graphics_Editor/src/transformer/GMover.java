package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import shape.GShape;

public class GMover extends GTransformer {

	public GMover(GShape selectedShape) {
		super(selectedShape);
	}

	@Override
	public void initTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().saveCurrentPosition(x, y);
		this.previous.setLocation(x, y);
		System.out.println("move "+getShape());
	}

	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().draw(g2D);
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(x  - this.getPrevious().getX(), y  - this.getPrevious().getY());
		this.selectedShape.transformShape(affineTransform);
		

		this.getShape().draw(g2D);
		this.previous.setLocation(x, y);
	}

	@Override
	public void finishTransforming(Graphics2D g2D, int x, int y) {

	}

	@Override
	public void moveALittle() {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 10);
		this.selectedShape.transformShape(affineTransform);
	}

}
