package frame;
import java.awt.Font;

import javax.swing.JMenuBar;

import global.GConstants.EFont;
import global.GConstants.EMenu;

public class GMenuBar extends JMenuBar {
	// attributes
	private static final long serialVersionUID = 1L;
    // components

	// associations
	private GDrawingPanel drawingPanel;
	
	public GMenuBar() {
		// components
		for (EMenu eMenu: EMenu.values()) {
			this.add(eMenu.getMenu());
			eMenu.getMenu().setFont(new Font(EFont.eMenuFont.getFont(), Font.PLAIN, EFont.eMenuFont.getFontSize()));
		}
	}
	
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		// initialize
		for (EMenu eMenu: EMenu.values()) {
			eMenu.getMenu().initialize(this.drawingPanel);
		}
	}
}
