package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

import global.GConstants.EAnchors;

public abstract class GShape implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// attributes
	private Shape shape;
	private boolean selected;
	private Color lineColor, fillColor;
	// components
	private GAnchors anchors;
	private AffineTransform affineTransform;
	public GShape(Shape shape) {
		this.shape = shape;
		this.selected = false;		
		this.anchors = new GAnchors();
		setAffineTransform(new AffineTransform());
		this.lineColor = Color.BLACK;
		this.fillColor = null;
	}
	public abstract GShape newInstance();
	
	public GShape clone(){
		try {
			return (GShape) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public double getWidth(){return this.shape.getBounds2D().getWidth();}
	public double getHeight(){return this.shape.getBounds2D().getHeight();}
	
	public Shape getShape() {return this.shape;}
	
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {this.selected = selected;}	

	public double getCenterX() {return this.shape.getBounds2D().getCenterX();}
	public double getCenterY() {return this.shape.getBounds2D().getCenterY();}

	public GAnchors getAnchors() { return anchors; }
	public void setAnchors(GAnchors anchors) { this.anchors = anchors; }
	
	public AffineTransform getAffineTransform() {return affineTransform;}
	public void setAffineTransform(AffineTransform affineTransform) {this.affineTransform = affineTransform;}
	
	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	public void draw(Graphics2D g2D) {
		if(this.shape instanceof GSelect){
			System.out.println("select");
		}
		if(this.fillColor != null){
			g2D.setColor(fillColor);
			g2D.fill(this.shape);
		}
		g2D.setColor(lineColor);
		g2D.draw(this.shape);
	}
	
	
	
	public void transformShape(AffineTransform affineTransform) {
		this.shape = affineTransform.createTransformedShape(this.shape);
		
	}
	
	
	public void drawAnchors(Graphics2D g2D) {
		this.anchors.draw(g2D, this.shape.getBounds2D());
	}
	
	public boolean contains(int x, int y) {
		if (this.selected) {
			if (this.anchors.contains(x, y)) {
				return true;
			}
		}
		return this.shape.contains(x, y);
	}
	public boolean lineContains(int x, int y) {
		if (this.selected) {
			if (this.anchors.lineContains(x, y)) {
				return true;
			}
		}
		return false;
	}
	public EAnchors getSelectedAnchor() {
		return this.anchors.getSelectedAnchor();
	}
	
	public Rectangle getBounds(){
		return shape.getBounds();

	}
	
	
	public abstract void saveCurrentPosition(int x, int y);	
	public abstract void setLocation(int x, int y);
	public abstract void move(int newX, int newY);
	public abstract void resize(int newX, int newY);
	
	
	//for GPolygon only
	public void addPoint(Point p) {
	}
	
	
	
	
	
}
