package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import global.GConstants.EAnchors;
import shape.GAnchors;
import shape.GShape;

public class GResizer extends GTransformer {
	Point2D resizeOrigin;
	
	public GResizer(GShape selectedShape) {
		super(selectedShape);
	}

	public Point2D getResizerOrigin(){
		Point2D p = new Point2D.Double();
		GAnchors anchors = this.selectedShape.getAnchors();
		switch (this.selectedShape.getSelectedAnchor()){
			case E: p.setLocation(anchors.getCcenterX(EAnchors.W), 0); break;
			case W: p.setLocation(anchors.getCcenterX(EAnchors.E), 0); break;
			case S: p.setLocation(0, anchors.getCcenterY(EAnchors.N)); break;
			case N: p.setLocation(0, anchors.getCcenterY(EAnchors.S)); break;
			case NE: p.setLocation(anchors.getCcenterX(EAnchors.SW), anchors.getCcenterY(EAnchors.SW)); break;
			case NW: p.setLocation(anchors.getCcenterX(EAnchors.SE), anchors.getCcenterY(EAnchors.SE)); break;
			case SE: p.setLocation(anchors.getCcenterX(EAnchors.NW), anchors.getCcenterY(EAnchors.NW)); break;
			case SW: p.setLocation(anchors.getCcenterX(EAnchors.NE), anchors.getCcenterY(EAnchors.NE)); break;
		default:
			break;
		}
		return p;
	}
	
	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		this.previous.setLocation(x, y);
		this.resizeOrigin = this.getResizerOrigin();
	}

	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		this.selectedShape.draw(g2D);
		
		AffineTransform affineTransform = new AffineTransform();
		
		affineTransform.setToTranslation(resizeOrigin.getX(), resizeOrigin.getY());
		
		Point2D resizerFactor = this.computeResizeFactor(this.previous, x, y);
		affineTransform.scale(resizerFactor.getX(), resizerFactor.getY());
		
		affineTransform.translate(-resizeOrigin.getX(), -resizeOrigin.getY());
		this.selectedShape.transformShape(affineTransform);
		
		this.selectedShape.draw(g2D);
		this.previous.setLocation(x, y);
		
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
	}
	
	
	private Point2D computeResizeFactor(Point2D previous, int x, int y){
		double px = previous.getX();
		double py = previous.getY();
		
		double deltaW = 0;
		double deltaH = 0;
		if (selectedShape.getSelectedAnchor() == EAnchors. NW ) {
			deltaW=-(x-px);
			deltaH=-(y-py);
		} else if (selectedShape.getSelectedAnchor() == EAnchors. N ) {
			deltaW=  0;
			deltaH=-(y-py);
		} else if (selectedShape.getSelectedAnchor() == EAnchors. NE ) { 
			deltaW=  x-previous.getX();
			deltaH=-(y-previous.getY()); 
		} else if (selectedShape.getSelectedAnchor() == EAnchors. W ) { 
			deltaW=-(x-previous.getX());
			deltaH=  0; 
		} else if (selectedShape.getSelectedAnchor() == EAnchors. E ) {
			deltaW=  x-previous.getX();
			deltaH=  0; 
		} else if (selectedShape.getSelectedAnchor() == EAnchors. SW ) {
			deltaW=-(x-previous.getX());
			deltaH=  y-previous.getY();
		} else if (selectedShape.getSelectedAnchor() == EAnchors. S ) {
			deltaW=  0;
			deltaH=  y-previous.getY();
		} else if (selectedShape.getSelectedAnchor() == EAnchors. SE ) {
			deltaW=  x-previous.getX();
			deltaH=  y-previous.getY();
		}
		double currentW = selectedShape.getWidth();
		double currentH = selectedShape.getHeight();
		
		double xFactor = 1.0;
		if (currentW > 0.0)
			xFactor = (1.0 + (deltaW / currentW));
		double yFactor = 1.0;
		if (currentH > 0.0)
			yFactor = (1.0 + (deltaH / currentH));
		
		return new Point.Double(xFactor, yFactor);
	}

	@Override
	public void moveALittle() {
		// TODO Auto-generated method stub
		
	}
}
