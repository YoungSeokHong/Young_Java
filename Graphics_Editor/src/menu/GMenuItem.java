package menu;

import javax.swing.JMenuItem;

import frame.GDrawingPanel;

public class GMenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;

	public GMenuItem(String name) {
		super(name);
	}
	
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	protected GDrawingPanel getDrawingPanel() {
		return this.drawingPanel;
	}
}
