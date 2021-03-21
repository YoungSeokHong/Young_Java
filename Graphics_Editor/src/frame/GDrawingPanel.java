package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import global.GConstants.EAnchors;
import shape.GGroup;
import shape.GPolygon;
import shape.GSelect;
import shape.GShape;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotater;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	// declarations

	private enum EDrawingState {
		eIdle, eTPTransforming, eNPTransforming
	};

	private enum EDrawingShape {
		eSelect, eRectangle, eLine, eCircle, ePolygon;
	};

	// attributes
	private static final long serialVersionUID = 1L;
	private EDrawingState eDrawingState;
	private EDrawingShape eDrawingShape;
	// components
	private Vector<GShape> shapeVector;
	private Vector<Vector<GShape>> undoList;
	private Vector<Vector<GShape>> doList;
	
	private Graphics2D g2D;
	// working variables
	private boolean updated;
	private GShape selectedShape;
	private GShape copiedShape;
	private GShape selectedTool;
	private GTransformer transformer;
	private Color mainColor;
	private Vector<GShape> selectedVector;
	private int undoCount;

	public GDrawingPanel() {
		super();

		this.eDrawingShape = null;
		// initialize components
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.shapeVector = new Vector<GShape>();
		this.undoList = new Vector<Vector<GShape>>();
		this.doList = new Vector<Vector<GShape>>();
		this.selectedVector = new Vector<GShape>();
		this.updated = false;
		this.selectedTool = null;
		this.transformer = null;
		this.undoCount = 0;
	}

	public void setLineColor(Color color) {
		if (this.selectedShape != null){
			mainColor = this.g2D.getColor();
			this.selectedShape.draw(g2D);
			this.selectedShape.setLineColor(color);
			this.selectedShape.draw(g2D);
			g2D.setColor(mainColor);
		}
	}

	public void setFillColor(Color color) {
		if (this.selectedShape != null){
			mainColor = this.g2D.getColor();
			this.selectedShape.draw(g2D);
			this.selectedShape.setFillColor(color);
			this.selectedShape.draw(g2D);
			g2D.setColor(mainColor);
//			this.selectedShape.draw((Graphics2D)this.getGraphics());
		}
		
	}
	
	public void setActionCommand(GShape selectedTool) {
		this.selectedTool = selectedTool;
	}

	public void initialize() {
		// initialize attributes
		this.setBackground(Color.WHITE);

		// initialize working variables
		this.selectedShape = null;
		this.eDrawingState = EDrawingState.eIdle;
		
		this.g2D = (Graphics2D) this.getGraphics();
		this.g2D.setXORMode(this.getBackground());

		this.repaint();
	}

	
	
	public Object getShapeVector() {
		this.updated = false;
		return this.shapeVector;
	}

	@SuppressWarnings("unchecked")
	public void setShapeVector(Object shapeVector) {
		if (shapeVector == null) {
			this.shapeVector.clear();
		} else {
			this.shapeVector = (Vector<GShape>) shapeVector;
		}
		this.initialize();
	}

	public boolean isUpdated() {
		return this.updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public Vector<GShape> getSelectedVector() {return selectedVector;}
	public void setSelectedVector(Vector<GShape> selectedVector) {this.selectedVector = selectedVector;}

	public void paint(Graphics g) {
		super.paint(g);
		for (GShape shape : this.shapeVector) {
			shape.draw((Graphics2D) g);
		}
	}

	private GShape onShape(int x, int y) {
		for (GShape shape : this.shapeVector) {
			if (shape.contains(x, y)) {
				return shape;
			}
			if (eDrawingShape == EDrawingShape.eLine) {
				if (shape.lineContains(x, y)) {
					return shape;
				}
			}
		}
		return null;
	}

	private void setSelected(GShape selectedShape) {
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(false);
			this.selectedShape.drawAnchors(g2D);
		}
		this.selectedShape = selectedShape;
		this.selectedShape.setSelected(true);
	}

	private void selectAction(int x, int y) {
		GShape shape = this.onShape(x, y);
		if (shape == null) {
			shape = this.selectedTool.newInstance();
			this.transformer = new GDrawer(shape);
		}else {
			if (eDrawingShape == EDrawingShape.eLine) {
				if (shape.lineContains(x, y)) {
					this.transformer = new GMover(shape);
				}
			} else {
				if(shape.getSelectedAnchor() == EAnchors.R){
					this.transformer = new GRotater(shape);
				} else if (shape.getSelectedAnchor() == null) {
					this.transformer = new GMover(shape);	
				} else {
					this.transformer = new GResizer(shape);			
				}
			}
		}
		this.setSelected(shape);
		this.updated = true;
		
	}


	private void initTransforming(int x, int y) {
		if(!selectedVector.isEmpty()){
			for(GShape shape : selectedVector){
				shape.drawAnchors(g2D);
			}
			selectedVector.clear();
		}
		
		this.undoCount = 0;
		Vector<GShape> undoVector = new Vector<GShape>();
		for(GShape shape :shapeVector){
			undoVector.add(shape.clone());
		}
		this.undoList.add(undoVector);
		
		this.shapeVector.remove(this.selectedShape);
		
		g2D.setXORMode(this.getBackground());
		this.transformer.initTransforming(g2D, x, y);
	}

	private void keepTransforming(int x, int y) {
		this.transformer.keepTransforming(g2D, x, y);
	}

	private void finishTransforming(int x, int y) {
//		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setColor(mainColor);
		g2D.setXORMode(this.getBackground());
		this.transformer.finishTransforming(g2D, x, y);
		
		if(selectedShape instanceof GSelect){
			this.selectedShape.draw(g2D);
			selectVector(selectedShape);
			System.out.println(this.selectedVector);
			for(GShape shape : selectedVector){
				shape.drawAnchors(g2D);
			}
			this.selectedShape = null;
		} else{
			this.selectedShape.drawAnchors(g2D);
			this.shapeVector.add(this.selectedShape);
		}
	}

	private void changeCursor(int x, int y) {
		GShape shape = this.onShape(x, y);
		if (shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else {
			EAnchors eAnchor = shape.getSelectedAnchor();
			if (eAnchor == null) {
				this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			} else {
				this.setCursor(new Cursor(eAnchor.getCursorType()));
			}
		}
	}
	
	private void selectVector(GShape range) {
		for (GShape shape : this.shapeVector) {
			if (range.contains(shape.getBounds().x, shape.getBounds().y)
					&& range.contains((int) shape.getBounds().getMaxX(), (int) shape.getBounds().getMaxY())) {
				this.selectedVector.add(shape);
			}
		}
	}
	
	public void cut() {
		this.copy();
		this.delete();
	}
	public void copy() {
		if(selectedShape != null){
			this.copiedShape = this.selectedShape.clone();
		}
	}
	public void paste() {
		Vector<GShape> undoVector = new Vector<GShape>();
		for(GShape shape :shapeVector){
			undoVector.add(shape.clone());
		}
		this.undoList.add(undoVector);
		
		if(this.copiedShape != null){
			this.transformer = new GMover(copiedShape);
			this.transformer.moveALittle();
			
			GShape newShape = this.copiedShape.clone();
			
			this.shapeVector.add(newShape);
			this.setSelected(newShape);
			
			this.selectedShape.draw(g2D);
			this.selectedShape.drawAnchors(g2D);
		}
	}
	
	public void delete() {
		if(this.selectedShape != null){
			Vector<GShape> undoVector = new Vector<GShape>();
			for(GShape shape :shapeVector){
				undoVector.add(shape.clone());
			}
			this.undoList.add(undoVector);
			
			this.selectedShape.draw(g2D);
			this.selectedShape.drawAnchors(g2D);
			this.shapeVector.remove(this.selectedShape);
			this.selectedShape = null;
		}
	}

	public void ddo() {
		if (!doList.isEmpty()&&undoCount>0) {
			Vector<GShape> undoVector = new Vector<GShape>();
			for (GShape shape : shapeVector) {
				undoVector.add(shape.clone());
			}
			this.undoList.add(undoVector);

			paint(g2D);

			System.out.println(doList);

			Vector<GShape> doVector = new Vector<GShape>();
			doVector = this.doList.lastElement();

			this.shapeVector.clear();
			this.shapeVector = doVector;

			this.doList.remove(doList.lastElement());
			this.setSelected(this.shapeVector.lastElement());
			this.selectedShape.drawAnchors(g2D);

			paint(g2D);
			undoCount--;
		}
	}
	
	public void undo() {
		if (!undoList.isEmpty()) {
			Vector<GShape> doVector = new Vector<GShape>();
			for (GShape shape : shapeVector) {
				doVector.add(shape.clone());
			}
			this.doList.add(doVector);

			paint(g2D);

			System.out.println(undoList);

			Vector<GShape> undoVector = new Vector<GShape>();
			undoVector = this.undoList.lastElement();

			this.shapeVector.clear();
			this.shapeVector = undoVector;

			this.undoList.remove(undoList.lastElement());
			this.setSelected(this.shapeVector.lastElement());
			this.selectedShape.drawAnchors(g2D);

			paint(g2D);
			undoCount++;
		}
	}
	
	public void group(GGroup group){
		if (!this.selectedVector.isEmpty()) {
			int i = 0;
			System.out.println(this.selectedVector);
			for (GShape shape : selectedVector) {
				group.add(shape);
				shapeVector.remove(shape);
				selectedVector.remove(shape);
				System.out.println(i);
				i++;
			}
		}

		this.setSelected(group);
		shapeVector.add(group);
		System.out.println(shapeVector);
	
		group.draw(g2D);
	}
	public void unGroup(){
	System.out.println(shapeVector);
	}
	
	public void close(){
		this.setVisible(false);
	}

	private void continueDrawing(Point p) {
		((GPolygon) selectedShape).continueDrawing(p);
	}
	
	class MouseHandler implements MouseInputListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if ((selectedShape instanceof GPolygon)&&(transformer instanceof GDrawer)) {
					if (e.getClickCount() == 1) {
						continueDrawing(e.getPoint());
					} else if (e.getClickCount() == 2) {
						finishTransforming(e.getX(), e.getY());
						eDrawingState = EDrawingState.eIdle;
					}
				}
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				selectAction(e.getX(),e.getY());
				initTransforming(e.getX(), e.getY());
				if((selectedTool instanceof GPolygon)&&(transformer instanceof GDrawer)){
					eDrawingState = EDrawingState.eNPTransforming;
				} else{
					eDrawingState = EDrawingState.eTPTransforming;
				}
			}
		}

		

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTPTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EDrawingState.eTPTransforming){
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				changeCursor(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eNPTransforming){
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	

	

}
